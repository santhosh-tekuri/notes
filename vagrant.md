# [Vagrant 2.0.1](https://www.vagrantup.com/)

```bash
$ vagrant version
$ vagrant help
$ vagrant list-commands
```

---

## Boxes

downloaded from <https://vagrantcloud.com/> to `~/.vagrant.d/boxes`

```bash
$ vagrant box add ubuntu/xenial64
$ vagrant box list
ubuntu/xenial64 (virtualbox, 20171221.0.0)
$ vagrant box remove ubuntu/xenial64
$ vagrant box update
```

to export use [vagrant-repackage.sh](files/vagrant-repackage.sh)  
* `vagrant-repackage.sh <name> <provider> <version>`
* creates `metadata.json` and `package.box`
* to import: `vagrant box add ./metadata.json`

---

## Vagrantfile

* `vagrant init <box-name>`
    * creates `Vagrantfile` in current directory  
    * vagrant environment stored in `.vagrant` subdirectory
    * add `Vagrantfile` to VCS and ignore `.vagrant` folder
* `Vagrantfile` is looked up from current directory upwards

```ruby
# by default host locale environment variable is passed to guest
# if guest software does not support host locale, do following
ENV["LC_ALL"] = "en_US.UTF-8"

# use configuration version 2
Vagrant.configure("2") do |config|
  # what box the machine will be bought up against
  config.vm.box = "ubuntu/xenial64"

  # seconds to wait to boot machine. defaults to 300
  config.vm.boot_timeout = 300

  # update to latest version if available. defaults to true
  config.vm.box_check_update = true

  # if nil, hostname is not managed. defaults to nil
  config.vm.hostname = "master"

  # --------------------[ Providers ] ---------------------

  # out of box: virtualbox, docker, hiperv (must be enabled in windows)
  #
  # default provider lookup:
  # 1. vagrant up --provider <name>
  # 2. VAGRANT_DEFAULT_PROVIDER environment variable
  # 3. try config.vm.provider calls in order
  # 4. try installed providers based on priority system
  # 5. raise error
  #
  # multiple machines can use different provider

  # prefer vmware fusion over virtualbox
  config.vm.provider "vmware_fusion"
  config.vm.provider "virtualbox"

  config.vm.provider "virtualbox" do |vb|
    vb.name = "my_vm" # defaults to <project-name><timestamp>
    vb.gui = true # do not use headless mode. defaults to false
    vb.memory = 1024
    vb.cpus = 2
  end

  # --------------------[ Multiple-Machines ]----------------

  # primary option allows to use this machine as default to "vagrant ssh"
  config.vm.define "web", primary:true do |web|
    web.vm.box = "apache"
  end

  config.vm.define "db" do |db|
    db.vm.box = "mysql"
  end

  # "vagrant up" starts all machines by default
  # autostart options can be turned to false
  # to start explicitly "vagrant up db_follower"
  config.vm.define "db_follower", autostart: false

  # looping over vm definitions
  (1..3).each do |i|
    config.vm.define "node-#{i}" do |node|
      node.vm.provision "shell", inline: "echo node #{1}"
    end
  end

  # --------------------[ Networking ] --------------------
  
  # multiple networks can be defined
  # networks are enabled in defined order

  # port forwarding
  config.vm.network "forwarded_port", guest: 80, host: 8080
  # protocol: "tcp" # either "tcp" or "udp". defaults to "tcp"

  # private network i.e. accessible only from host
  config.vm.network "private_network", type: "dhcp"
  # ip: "10.0.3.10" # use static ip, shouldn't collide with other machines
  
  # public network i.e accessible from LAN
  config.vm.network "public_network"
  # ip: "192.168.0.17" # use static ip, shouldn't collide with other machines
  # bridge: "en1: Wi-Fi (AirPort)" # interface vm should bridge to
                                   # prompts if not specified or has more than one interface

  # ------------------[ Synched Folders ]------------------

  # by default "." is synched with "/vagrant" in guest
  # avoid symbolic links 

  # first param is on host machine relative to project root
  # second param is on guest machine. must be absolute. created if necessary
  config.vm.synced_folder "src/", "/srv/website"
  # create: false # create host path if does not exist. defaults to false
  # owner: "rabbitmq" # defaults to ssh user
  # group: "rabbitmq" # defaults to ssh user. some sync types may not support this

  # to disable default synched folder /vagrant
  config.vm.synched_folder ".", "/vagrant", disabled: true

  # -----------------[ Provisioning ]----------------------

  # only run on "vagrant up"
  # to force provisioning "vagrant reload --provision"
  # do not run provisioners "vagrant up --no-provision"
  # can set "run" option to "always" in provisioner config  
  # mutliple provisioners are run in defined order
  # global scope are run before inner scope

  # naming a provisioner, useful to identify in vagrant output
  # provisioners in inner scope override provisioners with same name in global scope
  config.vm.provision "foo", type: "shell", inline: "echo hello"
  config.vm.define "web" do |web|
    web.vm.provision "shell", inline: "echo xxx"
    web.vm.provision "foo", type: "shell", inline: "echo HELLO"
    # preserve_order: false # false prints "xxx HELLO", true prints "HELLO xxx". defaults to false
  end

  # upload file/dir from host to guest
  # destination is overwritten 
  # uploaded using SCP. note ssh user might not have elevated privileges
  # supports trailing slashes or globing 
  config.vm.provision "file", source: "/host/folder", destination: "$HOME/guest/folder"

  $script1 = <<EOF
  string interpolation supported #{user.name}
  EOF
  $script2 = <<-EOF
  un-interpolated content
  EOF
  config.vm.provision "shell", inline: $script1
  # name: "installs nginx"
  # path: "install_nginx.sh" # relative to project or remote url
  # args: ["arg1", "arg2"]
  # env: { "v1" => "val1", "v2" => "val2" }
  # binary: false # if false do dos2unix. defaults to false
  # privileged: true # defaults to true

  config.vm.provision "ansible" do |ansible|
    ansible.playbook = "playbook.yml"
    ansible.ask_become_pass = false # prompt pwd when switching user with become/sudo. defaults to false
    ansible.ask_vault_pass = false # prompt valult pwd. defaults to false
    ansible.force_remote_user = true # use vagrant ssh user as default ansible remote user 
  end

  # install docker
  config.vm.provision "docker" do |d|
    d.build_image "/dir", args: "-t foo" # path must exist on guest
    d.images ["ubuntu", "rabbitmq"] # can be specified only once
    d.pull_images "nginx" # each call is added to images array
    d.pull_images "redis"
    d.run "db1",
      image: "mysql",
      cmd: "start-sql.sh"
      args: "-v '/vagrant:/var/www'"
  end
end
```
---

## DiskSize

Vagrant boxes by default has only 10GB. to change disksize:

```bash
$ vagrant plugin install vagrant-disksize

# to make sure disksize plugin installed
unless Vagrant.has_plugin?("vagrant-disksize")
    raise Vagrant::Errors::VagrantError.new, "vagrant-disksize plugin is missing. Please install it using 'vagrant plugin install vagrant-disksize' and rerun 'vagrant up'"
end

# to specify disksize
vagrant.configure('2') do |config|
    config.disksize.size = '50GB'
end
```

---

## CLI

* `vagnant status` for current environment
* `vagrant global-status` for all environments
* `vagrant validate` validates Vagrantfile
* `vagrant up`
* `vagrant suspend`
* `vagrant resume`
* `vagrant halt` poweroff machines. use `vagrant up` later
* `vagrant destroy -f`, use `-f` for no confirmation
* `vagrant ssh`
* `vagrant ssh-config`
* `vagrant port` show port mappings

---

## Arguments to Vagrantfile

<https://stackoverflow.com/a/31070025>

```ruby
require 'getoptlong'

opts = GetoptLong.new(
  [ '--opt1', GetoptLong::OPTIONAL_ARGUMENT ],
  [ '--opt2', GetoptLong::REQUIRED_ARGUMENT ],
  [ '--opt3', GetoptLong::NO_ARGUMENT ]
)

opt1 = 'val1'
opt2 = 'val2'
opt3 = false

opts.each do |opt, arg|
  case opt
    when '--opt1'
      opt1 = arg
    when '--opt2'
      opt2 = arg
    when '--opt3'
      opt3 = true
  end
end

Vagrant.configure("2") do |config|
  # configuration comes here
end
```

now use: `vagrant --opt1=val1 --opt2=val2 --opt3 up`

::: note
custom options must be specified before vagrant command
:::

---

### References

* <https://www.vagrantup.com/intro/getting-started/index.html>
* <https://www.vagrantup.com/docs/index.html>

#!/bin/bash -e

if [ "$#" -ne 3 ]; then
    echo "usage: vagrant-repakcage <name> <provider> <version>"
    exit 1
fi

name=$1
provider=$2
version=$3

vagrant box repakcage $name $provider $version

cat >metadata.json <<EOF
{
  "name": "$name",
  "versions": [
    {
      "version": "$version",
      "providers": [
        {
          "name": "$provider",
          "url": "package.box",
          "checksum_type": "sha1",
          "checksum": "`openssl dgst -sha1 package.box | cut -d " " -f 2`"
        }
      ]
    }
  ]
}
EOF

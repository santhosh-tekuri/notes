# Mac Setup

- Removed unused applications from Dock
- install [Anonymous Pro font](https://www.marksimonson.com/fonts/view/anonymous-pro)
- install [Cascadia Pro font](https://github.com/microsoft/cascadia-code/releases)
- Install git from terminal
- install [dotfiles](https://github.com/santhosh-tekuri/dotfiles) in home dir

---

## System Settings

- Sign In
    - Enter Credentials
    - iCloud
        - uncheck Contacts, Stocks, Home, Wallet, Siri
- Blootooth
    - uncheck
- General
    - Software Update
        - Automatic Updates
            - uncheck `Download new updates when available`
- Accessibility
    - Zoom
        - check `Use scroll gesture with modifier keys to zoom`
    - Spoken Content
        - check `Speak Selection`
    - Pointer Control
        - Trackpad Options
            - check `Use Trackpad for dragging`
            - Dragging Style `Without Drag Lock`
- Control Center
    - Battery
        - check `Show Percentage`
    - Menu Bar Only
        - Clock
            - Show date `Always`
            - check `Show the day of the week`
            - check `Show am/pm`
            - check `Announce the time`
            - interval `on the half hour`
        - Spotlight `Don't Show in Menu Bar`
- Siri & Spotlight
    - uncheck `Ask Siri`
    - Spotlight Privary
        - uncheck all except Applications, Calculator, System Preferences
        - add santhosh and backup
- Private & Security
    - Location Services
        - uncheck `Location Services`
- Desktop & Dock
    - check `Automatically hide and show the Dock`
    - uncheck `Show suggested and recent apps in Dock`
- Displays
    - select `More Space`
- Lock Screen
    - Require password after screen saver begins or display is turned off `immediately`
- TouchID & Password
    - add left and right finger prints
- Keyboard
    - Keyboard Shortcuts
        - Function Keys
            - check `Use F1, F2, etc. keys as standard function keys`
- Trackpad
    - Point & Click
        - Tracking speed: set to last but one
        - check `Tap to click`

---

## Safari
- Menubar
    - View/Show Status Bar
- General
    - File download location `Ask for each download`
    - uncheck `Open "safe" files after downloading`
- Advanced
    - check `Show full website address`
    - check `Show features for web developers`

---

## Finder

- Menubar
    - View > Show Status Bar
- General
    - New Finder windows show: `santhosh`
- Sidebar
    - check Favourites/santhosh
- Advanced
    - check `Show all filename extensions`
    - check all under `Keep folder on top:`
    - When performing a search: `Search the Current Folder`

---

## ITerm

- Profiles
    - General
        - set `Working Directory` to `Reuse previous session's directory`
    - Colors
        - Color Presets `Tango Dark`
        - foreground: `255,255,255`
        - background: `37,37,37`
        - Cursor Colors
            - check `Smart box cursor colorr`
    - Text
        - Font: anonymous pro size: 18 vertical spacing: 120
    - Window
        - columns: 150 rows: 35
    - Terminal
        - check `Unlimited scrollback`
    - Keys
        - General
            - Left Opton Key: `Esc+`
        - Keymappings
            - Alt-Left: send escape sequence b
            - Alt-Right: send escape sequence f
    - Advanced
        - Triggers
            - Regex: `^\++ [^ ].*` Action: `Highlight Text` Text: `White` Background: `40% Gray`
- Advanced
    - Disable the Tip of the Day ? `Yes`

---

## Terminal

- Profiles/Basic
    - Text
        - Background: `37,37,37`
        - Font anonymous pro size 18
    - Window
        - columns: 150 rows: 35

---

## [Homebrew](https://brew.sh)

- tmux
- wezterm
    - [enable undercurl](https://wezfurlong.org/wezterm/faq.html#how-do-i-enable-undercurl-curly-underlines)
- helix
- gitui
- git-delta
- fzf
- fd
- rg

--

## Rust

- [rustup](https://www.rust-lang.org/tools/install)


# Gno IntelliJ Plugin

This is an IntelliJ plugin that adds support for the Gno programming language. It allows IntelliJ to recognize `.gno` files and provides basic functionality for working with Gno files in the IDE.

## Requirements Version
IntelliJ or GoLand -> 2023.2+

Java JDK -> 17+ (Linux - MacOS - Windows)

Gradle -> 8.8+ (just Windows)

## Installation

To install this plugin in IntelliJ IDEA, follow these steps:

1. Clone this repository to your local machine:

    ```bash
    git clone https://github.com/gnolang/intellij-gno.git
    ```

2. Go to the repo
   ```bash
   cd intellij-gno
   ```
   
3. Build the plugin with Gradle:

    *macOS and Linux*
    ```bash
    ./gradlew build
    ```
    
    *Windows*
    ```bash
    gradle build
    ```
    
4. Open [gno](https://github.com/gnolang/gno) repo in **IntelliJ IDEA** or **GoLand IDEA**.

5. After building, you can install the plugin by following these steps:
   - Go to **File > Settings > Plugins**.
   - Click on the gear icon ⚙️ and select **Install Plugin from Disk**.
   - Navigate to the `build/distributions` directory and select the `.zip` file generated by the build.

6. Restart IntelliJ IDEA to activate the plugin.

## Usage

Once the plugin has been installed, IntelliJ IDEA will automatically recognize `.gno` files. You can create new `.gno` files or open existing ones, and the plugin will provide syntax highlighting and basic language support. You'll also get `gnopls` implementations, which will be automatically installed if you don't have the binary. 
with the following [features](https://github.com/gnolang/gnopls/tree/main/doc/features):
- [Gnopls](docs/Features.md#gnopls)
- [Highlighting](docs/Features.md#highlighting)
- [Formatting](docs/Features.md#formatting)
- [IntelliSense](docs/Features.md#intellisense)
- [Navigation](docs/Features.md#navigation)
- [Doc](docs/Features.md#doc)
- [Rename Symbol](docs/Features.md#rename-symbole)
- [Find Reference](docs/Features.md#find-reference)
- [Find Implementation](docs/Features.md#find-implementation)
- [Auto Install Import](docs/Features.md#auto-install-import)

‼️ To use gnopls, the [lsp4ij](https://github.com/redhat-developer/lsp4ij/tree/main) extension will be installed automatically when the `gno` plugin is installed.

## Development

To contribute or modify this plugin, follow these steps:

1. Fork the repository and clone it:

    ```bash
    git clone https://github.com/gnolang/intellij-gno.git
    ```

2. Open the project in **IntelliJ IDEA**.

3. Make sure you have the required dependencies installed by running:

    ```bash
    ./gradlew build
    ```

4. Run the plugin in a sandbox environment:

    ```bash
    ./gradlew runIde
    ```

This will launch a new instance of IntelliJ IDEA with the plugin loaded in a sandbox environment for testing.

If you want work directly with the IDE. Open the `Gradle` instruction

<img width="290" alt="image" src="https://github.com/user-attachments/assets/1c1f36b1-0528-4576-940f-993d88ddc8d3" />

## Building the Plugin

To build the plugin for distribution, use the following command:

```bash
./gradlew buildPlugin
```

The generated plugin file will be located in the `build/distributions` directory as a `.zip` file, which can be shared or uploaded to the JetBrains Plugin Repository.

## Contributing

Contributions are welcome! ✌🏼

## License

This project is licensed under the APACHE License. See the [LICENSE](LICENSE) file for more details.

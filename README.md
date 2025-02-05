# 📱 QR Generator and Scanner App

A simple **Android** app built using **Android Studio, Java, and ZXing** for generating and scanning QR codes. This app allows users to generate QR codes from text/URLs and scan QR codes using the device's camera.

---

## ✨ Features

- ✅ **Generate QR Codes**: Enter text or URL and generate a QR code instantly.
- 📸 **Scan QR Codes**: Use the device camera to scan and decode QR codes.
- 🔄 **Easy Sharing**: Share generated QR codes with others.

---

## 🛠 Technologies Used

- **Java**: Primary programming language.
- **ZXing**: Library for QR code generation and scanning.
- **Android Studio**: IDE for development.

---

## 🚀 Installation Guide

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/Prashid2002/GenQR.git
```

### 2️⃣ Open the Project in Android Studio

- Navigate to **Android Studio**.
- Click on **Open an Existing Project**.
- Select the cloned repository folder.

### 3️⃣ Sync Dependencies

- Wait for the project to sync and ensure all necessary dependencies are installed.
- If prompted, **install missing SDK components**.

### 4️⃣ Run the App

- Connect an **Android device** or use an **emulator**.
- Click on **Run ▶️** to launch the app.

---

## 📦 Dependencies

The project uses **ZXing** for QR code generation and scanning. Add the following to your `build.gradle` file:

```gradle
dependencies {
    implementation 'com.google.zxing:core:3.3.0'
}
```

---

## 📖 Usage

### 🔹 QR Code Generation

1. Open the app.
2. Enter the text or URL you want to convert to a QR code.
3. Tap **"Generate"** to create the QR code.
4. The generated QR code will be displayed on the screen.

### 🔹 QR Code Scanning

1. Tap the **"Scan"** button to open the camera.
2. Align the QR code in the scanning area.
3. The app will automatically scan and display the result.

---



---

## 🤝 Contributing

We welcome contributions! Follow these steps:

1. **Fork** the repository.
2. Create a **new branch**:
   ```bash
   git checkout -b feature-branch
   ```
3. Make your changes and commit:
   ```bash
   git commit -am 'Add feature'
   ```
4. Push to the branch:
   ```bash
   git push origin feature-branch
   ```
5. Open a **Pull Request**.

---

## 📜 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

---

### 🌟 Show Your Support

If you find this project useful, please ⭐ **star the repository** and share it with others!

Happy Coding! 🚀


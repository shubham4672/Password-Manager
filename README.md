# Password Manager

## Description

A console-based password manager built using Java and AES encryption. It allows users to store and retrieve passwords securely using symmetric encryption (AES), all stored in memory via a HashMap.

## Features

- Add new passwords for different sites.
- Retrieve stored passwords.
- Secure storage using AES encryption.

## Technologies Used

- Language: Java

- Encryption: AES (Advanced Encryption Standard)

- Storage: HashMap<String, String> (In-memory store)

- Console I/O: Scanner

- Encoding: Base64 (for storing encrypted data as readable strings)

## Setup Instructions

1. **Clone the repository:**
   ```bash
   git clone
   cd PasswordManager
   ```
2. **Compile the Java files:**
   javac PasswordManager.java
3. **Run the application:**
   java PasswordManager

### Usage

**Add Password:**

1. Choose the “Add Password” option.
2. Enter the site name and password.
3. The password will be encrypted and stored securely.

**Retrieve Password:**

1. Choose the “Retrieve Password” option.
2. Enter the site name.
3. The stored password will be decrypted and displayed.

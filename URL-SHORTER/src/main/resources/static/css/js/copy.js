function copyToClipboard() {
    const input = document.getElementById("shortUrl");
    input.select();
    input.setSelectionRange(0, 99999); // For mobile
    document.execCommand("copy");

    const message = document.getElementById("copyMessage");
    message.textContent = "✅ Link copied to clipboard!";
    setTimeout(() => message.textContent = "", 3000);
}

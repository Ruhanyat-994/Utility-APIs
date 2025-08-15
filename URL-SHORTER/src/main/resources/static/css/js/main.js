document.addEventListener("DOMContentLoaded", () => {
    const shortenBtn = document.getElementById("shortenBtn");
    const longUrlInput = document.getElementById("longUrl");
    const shortUrlDisplay = document.getElementById("shortUrl");
    const statsSection = document.getElementById("statsSection");
    const clickCountElem = document.getElementById("clickCount");
    const clickHistoryElem = document.getElementById("clickHistory");

    // Fetch stats for a given short code
    async function fetchStats(shortCode) {
        try {
            const res = await fetch(`/url-shorter/${shortCode}/stats`);
            if (!res.ok) throw new Error("Failed to fetch stats");

            const data = await res.json();

            clickCountElem.textContent = data.clickCount || 0;

            // Populate click history if available
            if (Array.isArray(data.clickHistory) && data.clickHistory.length > 0) {
                clickHistoryElem.innerHTML = "";
                data.clickHistory.forEach(ts => {
                    const li = document.createElement("li");
                    li.textContent = new Date(ts).toLocaleString();
                    clickHistoryElem.appendChild(li);
                });
            } else {
                clickHistoryElem.innerHTML = "<li>No clicks yet</li>";
            }

            statsSection.style.display = "block";

        } catch (err) {
            console.error(err);
            statsSection.style.display = "none";
        }
    }

    shortenBtn.addEventListener("click", async () => {
        const longUrl = longUrlInput.value.trim();
        if (!longUrl) {
            alert("Please enter a valid URL");
            return;
        }

        try {
            const res = await fetch("/url-shorter", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ longUrl })
            });

            if (!res.ok) {
                alert("Error shortening URL");
                return;
            }

            const data = await res.json();
            const shortCode = data.shortCode;

            if (!shortCode) {
                alert("Invalid response from server");
                return;
            }

            const fullShortUrl = window.location.origin + "/" + shortCode;
            shortUrlDisplay.textContent = fullShortUrl;
            shortUrlDisplay.href = fullShortUrl;

            // Fetch stats before redirect (optional, for debugging)
            await fetchStats(shortCode);

            // Redirect to short URL (this will trigger RedirectController)
            window.location.href = "/" + shortCode;

        } catch (err) {
            console.error("Error:", err);
            alert("An unexpected error occurred");
        }
    });
});

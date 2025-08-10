document.addEventListener("DOMContentLoaded", () => {
    const result = document.querySelector(".result");
    if (result) {
        result.style.opacity = 0;
        setTimeout(() => {
            result.style.transition = "opacity 0.5s ease";
            result.style.opacity = 1;
        }, 200);
    }
});

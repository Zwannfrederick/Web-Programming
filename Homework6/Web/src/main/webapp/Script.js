document.getElementById('firstName').addEventListener('input', function() {
    this.value = this.value.charAt(0).toUpperCase() + this.value.slice(1);
});

document.getElementById('lastName').addEventListener('input', function() {
    this.value = this.value.charAt(0).toUpperCase() + this.value.slice(1);
});

function togglePasswordVisibility() {
    let password = document.getElementById('password');
    let confirmPassword = document.getElementById('confirmPassword');
    let passwordFieldType = password.type === "password" ? "text" : "password";
    
    password.type = passwordFieldType;
    confirmPassword.type = passwordFieldType;
}

function validateForm() {
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;
    let errorMsg = document.getElementById("error-msg");

    if (password !== confirmPassword) {
        errorMsg.style.display = "block";
        return false;
    } else {
        errorMsg.style.display = "none";
        return true;
    }
}

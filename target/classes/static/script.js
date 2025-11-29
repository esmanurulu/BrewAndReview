document.addEventListener("DOMContentLoaded", function() {
    
    // Toplam Animasyon Süresi: 4.2 Saniye
    const animationDuration = 4200; 

    setTimeout(function() {
        const intro = document.getElementById('intro-container');
        const login = document.getElementById('login-container');
        const body = document.body;

        // İntro'yu gizle
        if(intro) intro.style.display = 'none'; 
        
        // Arka planı koyu yap
        if(body) body.classList.add('brown-mode');

        // Login formunu göster
        if(login) {
            login.style.display = 'block';
            setTimeout(() => {
                login.style.opacity = '1';
            }, 100);
        }

    }, animationDuration);

    // --- Giriş Formu ve Yönlendirme (Backend'e Bağlı) ---
    const form = document.getElementById('loginForm');
    if(form) {
        form.addEventListener('submit', function(e) {
            e.preventDefault();
            
            const formData = new FormData(form);
            
            fetch('/login', { 
                method: 'POST', 
                body: formData 
            })
            .then(response => response.json())
            .then(data => {
                if(data.status === 'success') {
                    const username = formData.get('username');
                    window.location.href = "/dashboard?user=" + username;
                } else {
                    alert(data.message);
                }
            })
            .catch(error => console.error('Hata:', error));
        });
    }
});
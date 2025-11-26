from flask import Flask, render_template, request, jsonify

# HATA VEREN SATIRI KAPATTIK (Yorum satırı yaptık)
# from flask_mysqldb import MySQL 

app = Flask(__name__)

# --- VERİTABANI AYARLARINI ŞİMDİLİK KAPATTIK ---
# app.config['MYSQL_HOST'] = 'localhost'
# app.config['MYSQL_USER'] = 'root'
# app.config['MYSQL_PASSWORD'] = 'sifreniz'
# app.config['MYSQL_DB'] = 'brew_review'
# mysql = MySQL(app)
# -----------------------------------------------

@app.route('/')
def index():
    # Siteye girince index.html'i göster
    return render_template('index.html')

@app.route('/login', methods=['POST'])
def login():
    # Veritabanı olmadığı için sahte bir giriş işlemi yapıyoruz
    data = request.form
    username = data.get('username')
    
    # Terminale bilgi yazdıralım
    print(f"Giriş Denemesi Yapıldı: {username}")

    return jsonify({"status": "success", "message": f"Hoşgeldin {username} (Tasarım Testi)"})

if __name__ == '__main__':
    app.run(debug=True)
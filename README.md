<div align="center">

  <img src="src/main/resources/static/images/bean.png" alt="Brew&Review Logo" width="120" height="120" />
  
  <h1>☕️ Brew&Review</h1>
  
  <p>
    <strong>Kahve Tutkunları İçin Sosyal Değerlendirme ve Keşif Platformu</strong>
  </p>

  <p>
    <a href="#-proje-hakkında">Proje Hakkında</a> •
    <a href="#-özellikler">Özellikler</a> •
    <a href="#-teknolojiler">Teknolojiler</a> •
    <a href="#-kurulum-ve-çalıştırma">Kurulum</a>
  </p>

  ![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
  ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
  ![MySQL](https://img.shields.io/badge/MySQL-Aiven_Cloud-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
  ![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Frontend-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)

</div>

---

## 📖 Proje Hakkında

**Brew&Review**, standart kafe rehberlerinin ötesine geçerek; kullanıcıların sadece mekanı değil, **baristaları** ve **menüdeki ürünleri** de ayrı ayrı değerlendirebildiği, **ziyaret geçmişini** tutabildiği ve **sosyal etkileşime** girebildiği (takip, favorileme) kapsamlı bir web uygulamasıdır.

Proje, **veri bütünlüğü** (Data Integrity) ve **performans optimizasyonu** (Index, View, Stored Procedure) odaklı geliştirilmiş olup, gerçek zamanlı bir **Bulut Veritabanı (Aiven MySQL)** üzerinde çalışmaktadır.

---

## ✨ Özellikler

### 👤 Kullanıcı (User) Modülü
* **Detaylı Değerlendirme:** Kafeye, spesifik bir ürüne (Örn: Latte) veya Baristaya ayrı ayrı puan ve yorum verme.
* **Check-in Sistemi:** "Buradaydım" diyerek ziyaret kaydı oluşturma (Ziyaret etmeden yorum yapılamaz kuralı).
* **Sosyal Ağ:** Diğer kullanıcıları ve favori baristaları takip etme.
* **Profil Yönetimi:** Ziyaret geçmişi, yapılan yorumlar, favori kafeler ve takipçi listesi.

### 👔 Yönetici (Manager) Modülü
* **İşletme Kaydı:** TC Kimlik ve İşyeri Ruhsat No ile kurumsal kayıt.
* **Kafe Yönetimi:** Kafe bilgilerini (Saat, Telefon) güncelleme, Menü ve Personel ekleme/çıkarma.
* **İstatistikler & Analiz:**
    * 🔥 **Trend Ürünler:** En çok tüketilen ve yorumlanan ürünlerin analizi.
    * 🚀 **Yoğunluk Analizi:** Kafenin en yoğun olduğu gün ve saat bilgisi.

### 🗺️ Harita & Keşif
* **Akıllı Arama:** Şehir, Kafe Adı veya "Tatlısı Var mı?" filtresi.
* **Sıralama:** A-Z veya Puana göre sıralama.
* **Harita Görünümü:** **Leaflet.js** ve **OpenStreetMap** entegrasyonu ile kafelerin harita üzerinde pinlenmesi.

---

## 🛠 Teknolojiler

Bu proje **MVC (Model-View-Controller)** mimarisi ile geliştirilmiştir.

| Alan | Teknoloji |
| :--- | :--- |
| **Backend** | Java 21, Spring Boot 3.2 (Web, Data JPA) |
| **Frontend** | Thymeleaf, HTML5, CSS3, JavaScript |
| **Database** | MySQL 8.0 (Aiven Cloud Hosting) |
| **Map API** | Leaflet.js & OpenStreetMap |
| **Tools** | Maven, Git, DBeaver |

---

## 🚀 Kurulum ve Çalıştırma

Projeyi yerel makinenizde çalıştırmak için aşağıdaki adımları izleyin:

### 1. Projeyi Klonlayın
Terminalinizi açın ve projeyi bilgisayarınıza indirin:
```bash
git clone [https://github.com/esmanurulu/BrewAndReview.git](https://github.com/esmanurulu/BrewAndReview.git)
cd BrewAndReview
2. Veritabanı Ayarlarını Yapın
Proje Aiven Bulut Veritabanı kullanmaktadır. Güvenlik nedeniyle veritabanı şifresi GitHub'da paylaşılmamıştır. src/main/resources/application.properties dosyasını açın ve password alanını proje ekibinden temin ettiğiniz şifre ile doldurun:

Properties

spring.datasource.url=jdbc:mysql://[brewandreview-uluesma8-d25c.b.aivencloud.com:22065/defaultdb?ssl-mode=REQUIRED](https://brewandreview-uluesma8-d25c.b.aivencloud.com:22065/defaultdb?ssl-mode=REQUIRED)
spring.datasource.username=avnadmin
spring.datasource.password=BURAYA_AIVEN_SIFRESINI_YAZIN
3. Projeyi Başlatın
Gerekli ayarları yaptıktan sonra aşağıdaki komutla uygulamayı başlatabilirsiniz:

Bash

./mvnw spring-boot:run
Uygulama başladığında tarayıcınızdan http://localhost:8080 adresine giderek kullanmaya başlayabilirsiniz.

<br /> <hr /> <div align="center"> Developed with ❤️ by <strong>Esmanur Ulu & Zeynep Yetkin</strong> </div>

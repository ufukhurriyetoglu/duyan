var demoModule = angular.module("demo", []);

function DemoController($scope, $http) {

	$scope.textArray = [
			{
				name : "  İçerik 1  ",
				sentence : "MHP Genel Başkanı Devlet Bahçeli, cumhurbaşkanı seçimi öncesinde ortak çatı aday belirleme temasları çerçevesinde CHP Genel Başkanı Kemal Kılıçdaroğlu ile görüştü."
			},
			{
				name : "  İçerik 2  ",
				sentence : "Yerel seçimlerde CHP’nin Ankara Büyükşehir Belediye Başkanlığı için aday gösterdiği eski MHP’li Mansur Yavaş, kendilerinden çalındığını düşündüğü seçim için hukuki mücadelesini sürdürüyor."
			},
			{
				name : "  İçerik 3  ",
				sentence : "6 gündür kapalı olan Diyarbakır - Bingöl karayolunun Lice mevkiinde bulunan göstericilere jandarma ekipleri ve özel harekat polisleri 7'nci günde müdahale etti."
			},
			{
				name : "  İçerik 4  ",
				sentence : "CHP Grup Başkanvekili Muharrem İnce, Twitter üzerinden 'Yalova'da kamp kuracağım' diyen Ankara Büyükşehir Belediye Başkanı Melih Gökçek'e çadır göndermesi yaptı: \"Yalova'da dün akşam apar topar terk edilmiş bir kamp çadırı bulunmuş! Yalova'da kamp kuracağım diyen kişiyi göreniniz var mı?''"
			},
			{
				name : "  İçerik 5  ",
				sentence : "AK Parti'den istifa eden İstanbul Bağımsız Milletvekili Hakan Şükür, Twitter'dan dershanelerin kapatılması ve PKK'nın kaçırdığı çocuklarla ilgili açıklama yaptı."
			},
			{
				name : "  İçerik 6  ",
				sentence : "Cumhurbaşkanı Abdullah Gül ve eşi Hayrünnisa Gül, Harvard Üniversitesi’nde ‘’Bilişim’’ okuyan oğulları Mehmet’in mezuniyet törenine katıldılar. Töreni Eski ABD Başkanlarından George H. Bush’un eşi Barbara ile birlikte izledi. Gül çiftinin oğulları Mehmet’in diploma töreninin yapılacağı fakülte salonuna ise üniversitenin isteğiyle basın mensupları alınmadı."
			},
			{
				name : "  İçerik 7  ",
				sentence : "CHP Lideri Kılıçdaroğlu bu sabah Ankara’da, Cumhurbaşkanlığı adaylığına ilişkin görüş alışverişinde bulunmak için Türkiye Kamu Sen’i ziyaret etti."
			},
			{
				name : "  İçerik 8  ",
				sentence : "İstanbul Valisi Hüseyin Avni Mutlu, Twitter hesabından yaptığı paylaşımlarla yine dikkat çekti. ''Dünyanın çivisi çıktı diyorlar....çakılan gökkazıklardan olabilir mi?'' diye tweet atan Vali'ye çok sayıda da yorum geldi."
			},
			{
				name : "  İçerik 9  ",
				sentence : "CHP Genel Başkan Yardımcısı Sezgin Tanrıkulu, TBMM Başkanı Cemil Çiçek’e Meclis’in bünyesindeki Saraylar için yapılan deprem hazırlıklarını sordu."
			},
			{
				name : "  İçerik 10  ",
				sentence : "Haklarında yolsuzluk ve rüşvet iddiası bulunan eski bakanlar Zafer Çağlayan, Egemen Bağış, Muammer Güler ve Erdoğan Bayraktar’la ilgili 5 Mayıs’ta TBMM Genel Kurulu’nda karar alınmasına karşın, soruşturma komisyonu kurulamadı. "
			},
			{
				name : "  İçerik 11  ",
				sentence : "İdareden onay alan 5 infaz koruma memuru ile 40 hükümlü kısa sürede alarmı yaptı. Cezaevi Müdürü Bayram Bozan, sistemin sorunsuz çalıştığını belirterek şöyle konuştu: “Alarm birimlerce de onaylandı. Ancak kurulumu için bakanlık onayı bekleniyor. Bunun seri üretimi de bakanlık ve genel müdürlüğün takdirinde. İstenilen cezaevine bu sistemi kurabiliriz.” "
			},
			{
				name : "  İçerik 12  ",
				sentence : "İSTANBUL'dan Konya’ya gelen Mustafa Tezcan (35), önceki gece arkadaşları Ö.F.K., M.E.E., ve Z.H. (33) ile buluşup uyuşturucu için sokak satıcılarına gitti. Satıcılarla kavga çıkınca, arkadaşları Tezcan’ı, kardeşi Arif Tezcan'ın (22) yanına götürdü. İddiaya göre, alkol ve uyuşturucu hap kullanan Tezcan’ın olayı anlattığı kardeşi, \"Ağabeyimi orada madara mı ettiniz? Siz nasıl adamlarsınız? Neden sıkmadınız?\" diye tepki gösterdi. Ö.F.K. de tabancasıyla yere tek el ateş etti. Arkadaşının hareketine sinirlenen abi, \"Sıkıyorsa bana ateş et\" dedi. Bunun üzerine çıkan kavgada Ö.F.K., Mustafa Tezcan’a ateş ederken, M.E.E. de pompalı tüfekle kardeşi Arif Tezcan'a ateş açtı. Ağır yaralanan kardeşler hastanede öldü. Ö.F.K. ve M.E.E kaçtıkları Balıkesir’de yakalandı."
			},
			{
				name : "  İçerik 13  ",
				sentence : "FEZLEKELERİ SAKLIYORLAR 17 Aralık’ta bir hükümetin, bir devleti nasıl soyduğuna tanık olundu. 4 eski bakanla ilgili soruşturma komisyonu kurulması 5 Mayıs’taki görüşmenin ardından fiilen başladı, ancak komisyon üyeleri belirlenmedi. 50 gündür bir arpa boyu yol alınamadı. 50 gündür engelleniyor. Neden? ‘Acaba cumhurbaşkanlığı seçimleri sonrasına bu komisyonun kuruluşunu oluşturabilir miyiz?’ Neden engelleniyor? Fezlekeler CHP’li üyelerin eline geçmesin diye. Şimdi ben kamuoyu önünde Sayın Cemil Çiçek’e soruyorum"
			},
			{
				name : "  İçerik 14  ",
				sentence : "Doğan bu suç duyurusunda, AYM ve BM Keyfi Tutuklamaları Önleme Çalışma Grubu’nun kararında yer alan ‘Adil Yargılanma Hakkı’nın ihlal edilmiş olduğuna dair tespitleri gündeme getirdi. BALYOZ davasında hükümeti ortadan kaldırmaya eksik teşebbüs ettiği gerekçesiyle 20 yıl hapis cezasına çarptırılan ve Anayasa Mahkemesi’nin (AYM) “Yeniden yargılansınlar” kararının ardından tahliye olan emekli Orgeneral Çetin Doğan, avukatı aracılığıyla Hâkimler ve Savcılar Yüksek Kurulu (HSYK) Başkanlığı’na dilekçe vererek İstanbul 10’uncu Ağır Ceza Mahkemesi heyeti hakkında suç duyurusunda bulundu. Mahkeme’nin eski heyeti hakkında daha önce de defalarca suç duyurusunda bulunan Doğan, AYM ve BM Keyfi Tutuklamaları Önleme Çalışma Grubu’nun kararında yer alan ‘Adil Yargılanma Hakkı’nın ihlal edilmiş olduğuna dair tespitlere dikkat çekti. HUKUKA AYKIRI Doğan’ın avukatı Hüseyin Ersöz’ün İstanbul Adli Yargı İlk Derece Mahkemesi Asalet Komisyonu Başkanlığı kanalıyla HSYK’ya gönderdiği dilekçede, şöyle denildi: “Dijital dokümanlarda manipülasyon yapıldığı tespitini içeren 6 askeri bilirkişi raporu ile 12 bilimsel mütalaanın tamamı kapatılan İstanbul 10’uncu Ağır Ceza Mahkemesi’nin dosyasında bulunduğu ifade edilerek, bu raporlarda yazan tespitler çerçevesinde bilirkişi incelemesi yaptırılması talep edilmişse de müvekkilin masumiyetini ortaya çıkartacak bu taleplerimiz hukuka aykırı olarak ‘sistematik’ bir şekilde reddedilmiş ve hakkında 20 yıl ile cezalandırılması yönünde hüküm kurulmuştur.” DİNLEMEDİLER Yargılama sırasında, iddia konusu askeri müdahaleyi önlediği ileri sürülen dönemin Kara Kuvvetleri Komutanı Aytaç Yalman ve Genelkurmay Başkanı Hilmi Özkök’ün dinlenmesi taleplerinin de reddedildiği belirtilen dilekçede “Ret kararları üzerine müteaddit defalar reddi hâkim talebinde ve HSYK şikâyetlerinde bulunulmuşsa da bu başvurulardan bir sonuç elde edilememiştir” ifadesi de yer aldı.             m Ayşegül USTA / İSTANBUL  "
			},
			{
				name : "  İçerik 15  ",
				sentence : "Annesiyle birlikte tam 90 hafta boyunca cumartesi günleri Beşiktaş’taki ‘Sessiz Çığlık’ eylemlerine katıldı. Balyoz davasının en genç subayı 40 yaşındaki Binbaşı Özgür Ecevit Taşçı’ydı. 13 yıl 4 ay hapis cezasına çarptırıldı. Hasdal Cezaevi’ne giderken, eşi Handan Taşçı ile birlikte o dönem 5 yaşında olan kızları Göksun’a bu durumu söylememe kararı aldılar. Göksun’a babasının birliğinde askerlerinin başında olduğu ve çok işi olduğu için akşamları eve gelemediği anlatıldı. Göksun ilk başta bu durumu kabul etmedi. Cezaevinde ziyaret ettiği babasına, “İşi bırak da eve gel” diye çok sitem etti. İlkokula başladıktan sonra okumayı öğrenince, ‘cezaevi’ tabelasını okudu. Babasının cezaevinde olduğunu öğrenmesi de böyle oldu. Oysa o ana kadar, her cumartesi katıldıkları ‘Sessiz Çığlık’ eylemi bir oyun, cezaevi ziyaretleri ise babasının işyerine geziydi. Üzüntüden kurdeşen oldu. Sadece o hafta babası için Beşiktaş’a gidemedi. Özgür Ecevit Taşçı geçen hafta İstanbul Anadolu Adalet Sarayı 4’üncü Ağır Ceza Mahkemesi tarafından yeniden yargılanma kararıyla tahliye oldu. Özgür Binbaşı’yı cezaevinin kapısında 7 yaşındaki kızı Göksun bekliyordu. Birbirlerine sımsıkı sarıldılar. Göksun o gece yine babasının koynunda uyudu. RIZKIMIZI YEDİLER Hande Taşçı evdeki ilk geceyi şöyle anlattı: “Göksun, Özgür’ün kucağından hiç inmedi. Orada da uyuyakaldı. Özgür yanağına bir öpücük kondurup yatağına götürdü. Göksun bir ara uyanıp korkuyla, ‘Babam nerede?’ diye sordu. Babasının eve gelmesini rüya sandı galiba. Özgür hava almak için balkona çıkmıştı. ‘Baban balkonda oturuyor’ dedim. Göksun yatağından kalkıp balkona gitti. Sımsıkı boynuna sarıldı. Birlikte yatıp uyudular.” Binbaşı Özgür Ecevit ise “Birileri bizim kul hakkımızı yedi. Kızımızın rızkını yedi. Ve o insanlar kendilerine Müslüman diyebiliyor hâlâ. Çocuklardan babalarını, kadınlardan kocalarını çaldılar” dedi."
			},
			{
				name : "  İçerik 16  ",
				sentence : "PM, Kılıçdaroğlu’nun isteğiyle temmuz ayında yapılması planlanan olağan kurultayı da 1 yıl erteledi. Tüm lehte ve aleyhte değerlendirmeleri dinleyen Kılıçdaroğlu “STK’lar, meslek odaları, kanaat önderleriyle görüşmelerden ‘Toplumu kutuplaştırmayacak aday’ profili öne çıktı. Sayın İhsanoğlu bu tanıma en uygun isimlerden biridir.Önyargılı olmayın. Haklılığımız ortaya çıkacak, halkımız da gereken desteği verecek” dedi. BBP, DP ve DYP’den de İhsanoğlu’na destek açıklamaları geldi. ‘Eski’lerden bildiri GEÇMİŞ dönemlerde CHP'de milletvekili ve bakan olarak görev yapmış 42 siyasetçi, Ekmeleddin İhsanoğlu’nun cumhurbaşkanı adayı gösterilmesine karşı olduklarını belirten bir bildiri yayınladı. İhsanoğlu'nun, “Atatürk ilke ve devrimlerine duyarlı bir aday olmadığı” savunuldu. Bildiriye imza atanlar arasında Deniz Baykal’a yakınlığıyla bilinen isimler de var: “Algan Hacaloğlu, Kemal Anadol, Onur Öymen, Şahin Mengü, Cevdet Selvi, İsmail Özay, Tayfun İçli, Necla Arat, Fuat Çay, Enis Tütüncü, Tacidar Seyhan, Mesut Değer, Şerif Ertuğrul, Mehmet Sevigen, Mahmut Yıldız, Canan Arıtman.” AA Her fotoğraf için 5 oy lazım SABANCI Üniversitesi'nin cumartesi akşamı Tuzla'daki yerleşkesinde düzenlediği mezuniyet töreninde seyirciler arasında bulunan Cumhurbaşkanı adayı Ekmeleddin İhsanoğlu, mezunların ve velilerin ilgi odağı oldu. Eşi Füsun İhsanoğlu'nun yeğeni Ece Oğuzkan'ın mezuniyet törenini izleyen İhsanoğlu, kendisini fark ederek yanına gelen vatandaşlarla sohbet etti. Bazı mezunlarla ve vatandaşlarla fotoğraf, bazılarıyla selfie çektiren İhsanoğlu, esprili bir dille de destek istedi. İhsanoğlu, \"Her fotoğraf için 5 oy lazım\" dedi. Törende, Ece Oğuzkan anons edildiğinde İhsanoğlu ayağa kalkarak bir süre alkışladı. Mehmet AKTARAN / DHA  "
			},
			{
				name : "  İçerik 17  ",
				sentence : "Saat 10.40 sıralarında 2 koruması, avukatı ve basın danışmanı ile Adalet Sarayı’na gelen İhsanoğlu, vatandaşların kullandığı C Kapısı’ndan girerek X-Ray cihazından geçti. İhsanoğlu’nu özel güvenlik görevlileri “Hoşgeldiniz” diyerek karşıladı. 4’üncü katta bulunan Adli Sicil Bürosu’na giden İhsanoğlu’nun sıraya girdiğini gören personel içeriye davet ederek bekletmeden evrakını hazırladı. Adli sicil bürosunda işlemleri 5 dakika süren İhsanoğlu memurlarla tokalaştıktan sonra yine vatandaş girişinden çıktı.  Gazetecilerin sorularını yanıtlayan İhsanoğlu şunları söyledi: “Sebebi ziyaretimiz, müracaat dosyasını tamamlamak için gerekli adli sicile ait kaydı almaktır. Bizzat kanundaki hükme göre şahısların kendilerinin alması lazım yani vekalet yolları da var ama ben kendim gelip almak istedim. Sağ olsunlar iki dakikada verdiler. Demek ki endişe edecek bir şey yok.” HERKES HOŞ KARŞILIYOR Bir gazetecinin,  “Personel sizi hoş karşıladı” sözleri üzerine İhsanoğlu, “Herkes Allah’a şükür gittiğimiz her yerde çok hoş karşılıyor. Müspet ve ince ifadelerle, hayırhah düşüncelerle iyi temennilerini ifade ediyorlar” dedi.  "
			},
			{
				name : "  İçerik 18  ",
				sentence : "Duruşmaya, davanın 1 numaralı sanığı olarak gösterilen ve geçen hafta Balyoz davasından tahliye edilen emekli Orgeneral Hurşit Tolon da katıldı. Geçen hafta tahliye olan Kurmay Albay Ahmet Küçükşahin de asker sanıklara destek vermek için izleyici sıralarında yer aldı. Savunmasında bağımsız yargıya olan inancını kaybettiğini belirten Tolon, şunları söyledi: “Ben buraya iddia makamının üzerime atmış olduğu asılsız suçlamalara savunma yapmak için gelmedim. Tahliye olduktan sonra burada olma nedenim"
			},
			{
				name : "  İçerik 19  ",
				sentence : "Dönemin İliç Cumhuriyet Savcısı olan Bayram Bozkurt, Ergenekon savcılarına gizli tanık ‘Efe’ kod adıyla ifade vermiş ve Erzincan’daki Ergenekon davasının açılmasına neden olmuştu. Gizli tanık ‘Efe’, Dursun Çiçek tarafından hazırlandığı ileri sürülen İrtica ile Mücadele Eylem Planı’nın dönemin Erzincan Başsavcısı İlhan Cihaner, 3. Ordu Komutanı Orgeneral Saldıray Berk ve bir grup asker tarafından uygulamaya konulduğunu iddia etmişti. Bozkurt’un ifadeleri doğrultusunda Eskişehir İl Jandarma Komutanı Albay Recep Gençoğlu, eski Erzincan Jandarma İstihbarat Kısım Komutanı Astsubay Şenol Bozkurt, dönemin 3. Ordu Komutanı Saldıray Berk ve İlhan Cihaner hakkında ‘örgüt üyeliği’nden dava açılmıştı. Bozkurt kimliğinin deşifre olmasından yaklaşık 1 ay sonra, 25 Haziran 2010’da Adalet Bakanlığı’na dilekçe sunarak 7 Temmuz 2010’da emekli oldu. Daha sonra tanık koruma programına alınan Bozkurt’un estetik operasyonla yüzü ve kimliği değiştirildi. Savcı Bozkurt, yasal düzenlemelerle mesleğe geri dönmek için HSYK’ya eski kimliğiyle başvurdu. HSYK da 5 Mart 2013’te Bozkurt’un mesleğe dönmesine karar verdi. Bozkurt yeni kimliğiyle Cumhuriyet Savcısı olarak Ankara’nın bir ilçesine atandı. Bozkurt’un eşi ise sağlık bakanlığı’nın görevlendirmesiyle 4 Eylül 2013’te ABD’ye gitti. Bozkurt da eşinin ardından 1 yıl ücretsiz izin alarak 16 Aralık 2013’te ABD’ye gitti. Ancak HSYK, incelemesi sonucunda Savcı Bozkurt’un sicil bilgilerini gizlediğini belirledi. BOZDAĞ İTİRAZ ETTİ Bunun üzerine HSYK 1. Dairesi Başkanı İbrahim Okur ve üyeler, Adalet Bakanı Bekir Bozdağ’a başvurarak karara itiraz hakkını kullanmasını istedi. Hürriyet’e konuşan Okur, Bozkurt’un HSYK’ya göreve iadesi sırasında disiplin cezalarına ilişkin bilgilerin olmadığının fark edildiğini"
			},
			{
				name : "  İçerik 20  ",
				sentence : "5, 6 ve 7’nci sınıf öğrencilerinden 573 bin 443’ü seçmeli ders olarak matematiği tercih etti. Dini dersler ise ilk 3 sırada yer alamadı.  Milli Eğitim Bakanlığı’na 19 Haziran akşamına kadar ulaşan bilgilere göre, ortaokul 5, 6 ve 7’nci sınıf öğrencileri seçmeli dersler arasından en çok matematik, İngilizce ile spor ve fiziki etkinlikler dersini tercih etti. Bakanlık, mayıs ayının son haftasında 81 ilin valiliğine bir yazı göndererek ortaokul ve imam hatip ortaokullarındaki öğrencilerin seçmeli ders tercihlerini 2-13 Haziran arasında yapmasını istedi. Öğrenciler de verilen süre içinde istedikleri dersleri yazdı. Buna göre, ortaokul ve imam hatip ortaokullarında okuyan 5, 6 ve 7’nci sınıf öğrencilerinin 573 bin 443’ü matematik dersini tercih etti. İkinci sırada ise 419 bin 175 öğrencinin seçtiği İngilizce yer aldı. Spor ve fiziki etkinlikler dersini de 362 bin 953 öğrenci almak istedi. Böylece öğrencilerin en çok tercih ettikleri dersler sıralamasında ilk üçü sırasıyla matematik, İngilizce ile spor ve fiziki etkinlikler dersleri aldı. En çok gündeme gelen ve merak edilen dini dersler ise ilk 3’e giremedi. Dini dersler arasında yer alan Kuran-ı Kerim dersini 311 bin 965 öğrenci tercih etti. Hz. Muhammed’in Hayatı’nı seçenlerin sayısı da 180 bin 515 olurken, temel dini bilgileri seçenlerin sayısı ise 100 bin oldu. Yaşayan diller ve lehçeler başlığındaki derslerden ise en çok ilgi Kurmançi ve Zazaki’yi içeren Kürtçe’ye oldu. 2’nci sırada Lazca, 3’üncü sırada Adigece ve 4’üncü sırada da Abazaca yer aldı. GERÇEKÇİ AKIL DOLU YAKLAŞIM Prof. Dr. Betül Tanbay (Türk Matematik Derneği Başkanı) Gerçekçi ve akıl dolu bir yaklaşım var ortada. 600 bin civarında öğrenci, annesi ve babasıyla karar veriyor seçmeli derslere. Çocukları ve ailelerini de kattığımızda milyonlardan bahsediyoruz. Milyonlarca insan matematik dersinin geliştirilmesi gerektiğini düşünüyor. Çoluk çocuk, ailleler oturdukları zaman hayattaki ilk tercihlerinin matematik, ikinci tercihlerinin de İngilizce olduğunu düşünüyorlar. Matematik ve yabancı dilin geliştirilmesi gereken bir konu olduğunun bilincindeler.                                 Hayalleri online derslerin olduğu üniversiteler 21 ülkeden, 37 okulda, 20 bin 800 öğrenci arasında yapılan antekete göre, üniversitelilerin yüzde 43’ü gelecekte yükseköğretim kurumlarında ücretsiz ve online içerik olmasını istiyor. Yüzde 59’u ve Türkiye’den katılanların yüzde 60’ı, gelecekte sosyal medya platformlarından ders almayı, farklı dillerde eğitim verilmesini, kişiselleştirilmiş özel online ders sistemlerinin kurulmasını, müfredatın grup projelerine dayalı olmasını ve esnek ders programlarının yapılmasını hayal ediyor. Laureate Üniversite Ağı, gençlere geleceğin yükseköğretim kurumlarını nasıl hayal ettiklerini sordu. İstanbul Bilgi Üniversitesi öğrencilerinin de aralarında bulunduğu toplam 20 bin 800 üniversiteli ile gerçekleştirilen ankete göre, gelecekte üniversitelerin temel konusu erişilebilirlik, esneklik, yenilikçilik ve iş odaklılık olacak. Araştırma sonuçlarına göre, katılımcıların yüzde 68’i gelecekte üniversitelerin, öğrencilerin ders materyallerine, kitaplarına ve diğer referans kaynaklarına ulaşabilecekleri ücretsiz online kütüphaneler olmasını istiyor. Yüzde 52’si gelecekte derslerin çoğunun günün her saatinde yapılabilmesini, yüzde 44’ü ders programlarının geleneksel ders takvimleri yerine çalışan öğrencilere  belirlenmiş saatler dışında eğitim almayı tercih edenlere uygun şekilde düzenlenmesini hayal ediyor."
			},
			{
				name : "  İçerik 21  ",
				sentence : "Hürriyet Gazetesi Yazarı Yalçın Bayer ‘En iyi köşe yazarı’ seçildi. 653’üncü Tarihi Kırkpınar Güreşleri’nin ardından 1’inci Kırkpınar Yılın En İyileri Ödül töreni Tarihi Ekmekçizade Ahmet Paşa Kervansarayı’nda yapıldı. Ödül törenine Edirne Valisi Dursun Ali Şahin, Edirne Belediye Başkanı CHP’li Recep Gürkan da katıldı. Bayer ödülünü CHP Burdur Milletvekili Ramazan Kerim Özkan’ın elinden aldı.  "
			},
			{
				name : "  İçerik 22  ",
				sentence : "Ankara Büyükşehir Belediyesi Basın Merkezinden yapılan açıklamada, Gökçek, hacklenen Twitter hesabının saat 16.00’dan itibaren kendi kullanımına açıldığı bildirildi. Gökçek, “03.59’dan itibaren kimliği belirsiz kişilerce ele geçirilen Twitter hesabım, Twitter üst yönetiminin müdahalesi neticesinde bugün saat 16.00 itibarı ile şahsımın kullanımına açılmıştır. Bu süre içerisinde hesabımdan yapılan açıklama ve duyurularla hiçbir ilgim yoktur. Kamuoyunun bilgisine sunarım” açıklamasını yaptı. NE OLMUŞTU Türkiye haftanın ilk gününe Melih Gökçek'in Twitter hesabından atılan 'tuhaf' tweet'lerle başladı. Gökçek'e ait olan ve 2 milyonu aşkın takipçi sayısına sahip \"06melihgokcek\" hesabını kendilerini Siber Ergenekon Tim olarak tanıtan bir grup hacker tarafından hack'lendi. 4 saat içinde 36 tweet atan hacker'ların ilk tweet'i \"Ne mutlu Türküm diyene - Mustafa Kemal Atatürk\" - Siber Ergenekon Tim\" mesajı oldu. Kısa sürede 2 binden fazla retweet alan mesajın ardından \"EGO otobüslerini bu sabah itibari ile bedava yapıyorum lan\" ve \"İtiraf ediyorum fişkiyeyi ben kırdım\" yazan hacker'ler seslerini duyurmayı başardı. Başbakan Erdoğan'IN HESABINI DA ENGELLEDİ Başbakan Erdoğan'ın 4 milyonun üzerinde takipçiye ulaşan sözde hesabını da engelleyen ve bunun fotoğrafını paylaşan hacker'lar hesabın Twitter profil fotoğrafındaki Gökçek'in resmini değiştirmezken, kapak fotoğrafını değiştirmeyi ise ihmal etmedi. SANSASYONEL TWITTER MESAJLARI"
			},
			{
				name : "  İçerik 23  ",
				sentence : "10’uncu sınıf öğrencisi gençlerden Alkaya kaza yerinde, Uzun ise kaldırıldığı hastanede yaşamını yitirdi. Kazanın ardından kaçan otomobilin sürücüsü Mazlum Savaş, İzmir'de polis tarafından yakalanıp gözaltına alındı.  "
			},
			{
				name : "  İçerik 24  ",
				sentence : "TÜRKİYE genelinde yollar, yapılan tüm uyarılara, her yıl can veren ya da yaralanan yüzlerce kişiye rağmen, 2014 yılının ilk 5 ayında da yine kan gölüne döndü. Emniyet Genel Müdürlüğü, 2014 yılının Ocak-Mayıs döneminin trafik kaza bilançosunu açıkladı. Ülke genelinde 839’u polis, 365’i de jandarma sorumluluk bölgesinde olmak üzere 142 bin 591 kaza meydana geldi. Kazalarda 1204 kişi hayatını kaybetti, 98 bin 639 kişi yaralandı. ÇOĞU POLİS BÖLGESİ Geçen mayısta polis sorumluluk bölgesinde 28 bin 45 trafik kazası meydana geldi. Bu kazalarda 12 bin 35 kişi yaralandı, 200 kişi de olay yerinde hayatını kaybetti. Jandarma sorumluluk bölgesinde ise mayıs ayında 3 bin 34 trafik kazası meydana geldi. Bu kazalarda 108 kişi hayatını kaybederken 3 bin 992 kişi yaralanarak hastaneye kaldırıldı. 2014 yılının ilk 5 ayının bilançosu ise şöyle: 839’u polis sorumluluk bölgesinde, 365’i de jandarma sorumluluk bölgesinde olmak üzere meydana gelen kazalarda toplam 1204 kişi öldü, 98 bin 639 kişi yaralandı. 191 ZİNCİRLEME KAZA Yılın ilk 5 aylık döneminde meydana gelen kazalardan 181’i hayvana çarpma, 191’i zincirleme, bin 386’sı duran araca çarpma, 12 bin 457’si yayaya çarpma şeklinde meydana geldi. Kazalarda yine en büyük kusur sürücülerin oldu. 50 bin 172 kaza sürücü kusurundan, 6 bin 729 kaza yaya kusurundan kaynaklandı. Bin 24 kaza alkollü araç kullanan sürücülerden, 1048 kaza da şerit ihlalinden kaynaklandı."
			},
			{
				name : "  İçerik 25  ",
				sentence : "Savcı, hırsızlık suçu işleyenler için bile tutuklama kararı verilirken, siyasal ve askeri casusluk suçlaması yöneltilen şüphelilerin tutuklanmamalarının hukuka uygun olmadığını savundu. Soruşturmayı sürdüren Savcı Durak Çetin’in itirazını önce şüpheli polislerin tutuksuz yargılanması yönünde karar veren Ankara 9. Sulh Ceza Mahkemesi değerlendirecek. İŞKENCEDEN SUÇ DUYURUSU Savcılığın itirazının sonucu beklenirken, 5 şüpheli polisin avukatı Mehmet Sürer, müvekkillerinin gözaltında işkence ve kötü muameleye maruz kaldıkları gerekçesiyle kolluk kuvvetleri hakkında  ‘işkence’, ‘adil yargılamayı etkilemeye teşebbüs’, ‘hakaret’, ‘kişiyi hürriyetinden yoksun bırakma"
			},
			{
				name : "  İçerik 26  ",
				sentence : "Gebze bir çağrı merkezinde çalışan Mehtap Civelek, eşinden şiddet gördüğünü, aralarında geçimsizlik olduğunu belirterek boşanma davası açtı. Volkan Civelek (32) eve dönmesini istediği eşini ölümle tehdit etmeye başladı. Genç kadına polis koruması verildi. ‘BAŞKA ERKEKLE GÖRÜŞME TAHRİK’ İddiaya göre Mehtap Civelek dava açtıktan sonra, çalıştığı firmada şoförlük yapan Barış isimli biriyle görüşmeye başladı. Volkan Civelek de bunu bahane ederek Mehtap Civelek’i öldürdü. Volkan Civelek’e Gebze 2’nci Ağır Ceza Mahkemesi’nde ömür boyu hapis istemiyle dava açıldı. Savcı, Mehtap Civelek’in başka biriyle ilişkisi olduğu kuşkusu bulunduğunu belirterek, Volkan Civelek’in cezasında tahrik indirimi yapılmasını istedi. Kadın Cinayetlerini Durduracağız Platformu üyelerinden Sıla Gemicioğlu ise “Genç kadının başka biriyle görüşmesi tahrik indirimi için neden olamaz” dedi. VİCDANA SIĞMAZ SAVCIYA tepki gösteren Mehtap Civelek’in ağabeyi İbrahim Bülbül, “Kardeşim boşanma davası açtıktan 6 ay sonra öldürüldü. Savcının kuşku nedeniyle indirim istemesi vicdana sığmaz. Boşanma davası açıldıktan sonra istediği kişiyle görüşmüş olabilir. Dosyada bununla ilgili delil de bulunmamaktadır” dedi.  "
			},
			{
				name : "  İçerik 27  ",
				sentence : "Yaklaşık 50 kişiden oluşan grup Metris, Ümraniye, Gebze, Kandıra ve Bolu cezaevinin önünde basın açıklaması yaptıktan sonra yürüyerek Ankara’ya gidecek. Grup, hasta tutukluların isimlerinin bulunduğu pankart açıp ‘Hasta tutsaklar serbest bırakılsın, Tedavi haktır engellenemez’ sloganları attı. 235’i ağır olmak üzere 642 hasta tutuklu olduğunu belirten İHD İstanbul Şubesi Başkanı Abdülbaki Boğa, “12 Eylül dönemine ait yasalar günümüzde de devam etmektedir. Göz boyamak adına bir yargılama yapıldı, ancak o dönemin bütün uygulamaları sürüyor. Cezaevlerinde bulunan yüzlerce hasta tutsak için Ankara’ya gidiyoruz” dedi. Daha sonra grup tutukevi önünden O-1 karayoluna kadar yürüyüşe geçti."
			},
			{
				name : "  İçerik 28  ",
				sentence : "HDP Batman Milletvekili Ayla Akat Ata’nın soru önergesini yanıtlayan Bozdağ, süngerli odaların kapalı devre kamera sistemiyle takip edildiğini belirterek “Kendisine veya başka kişilere zarar verme ihtimali bulunan hükümlü ve tutukluların bu odalara konulması disiplin cezası değil, geçici süreli bir tedbir niteliğindedir” dedi.  "
			},
			{
				name : "  İçerik 29  ",
				sentence : "Almanya'nın Ankara Büyükelçiliği'nden bir heyeti kabul eden bağımsız Mardin Büyükşehir Belediye Başkanı Ahmet Türk, belediyeyi büyük bir borç yükü ile devraldıklarını ve oturacak bir binalarının dahi bulunmadığını söyledi. Mardin'in bir turizm kenti olduğunu ve kültürüne uygun projelerle kenti daha ön plana çıkarmak istediğini ifade eden Türk, sözü 1950'li yıllardan beri NATO tarafından Radar Üssü olarak kullanılan Mardin Kalesi'ne getirdi. MARDİN KALESİ   \"ORAYA PARKLAR YAPMAK İSTİYORUZ\" Türk, Mardin Kalesi'nin 50-60 yıldan beri askeri bir NATO Üssü olarak kullanıldığını, ama 50 yıllık teknoloji ile Ortadoğu ve Suriye'nin gözetlenemeyeceğini, daha önce orayı boşaltma kararı almalarına rağmen halen kalenin boşaltılmadığını söyledi. Türk, \"Mardin Kalesi'nde halen asker ve MİT kalıyor ve orayı kullanıyorlar. Bu çağda artık 50 yıl önceki teknoloji ile izleme ve gözetleme yapılamaz. Bunu kendileri de iyi biliyor. Turizm kenti olan Mardin'de bu görüntü yakışmıyor. Mardin Kalesi'nin boşaltılması ile orayı turizme açıp parklar yapmak istiyoruz\"dedi. \"TÜRKİYE MARDİN GİBİ OLSAYDI...\" Türk, 'Kürdistan' diye adlandırdığı coğrafyanın sadece Kürtlerin değil, 5 bin yıldır Süryanilerin, Ermenilerin, Kürtlerin birlikte yaşadığı bir coğrafya olduğunu belirterek, \"Mardin merkezinde ve kimi ilçelerinde Arap halkı da yaşıyor. Bu halkların hakkını ve hukukunu inkar etmeden bir yönetim anlayışını gerçekleştirmek istiyoruz. Türkiye, Mardin gibi olsaydı, buradaki halklar gibi yaşasalardı şu anda Türkiye’de herhangi bir sorun kalmazdı\" dedi"
			},
			{
				name : "  İçerik 30  ",
				sentence : "Kaza, sabaha karşı saat 04.30 sıralarında, Mudanya Yeni Mahalle’de meydana geldi. İddiaya göre, dün girdikleri LYS sınavının stresini atmak isteyen 7 öğrenci, bir eğlenceye katıldıktan sonra deniz manzarası izlemek üzere kiraladıkları otomobil ile Mudanya İlçesi’ne gittiler.   KAZADAN FOTOĞRAFLAR Yamaçtaki bir tepeden Mudanya manzarası izleyen gençlerin bulunduğu otomobil, Yeni Mahalle Gül Sokak’ta bir virajı alamayarak, Marmara Yazlık Evleri’nin bahçesine düştü. Kimin kullandığı belirlenemeyen otomobilde bulunan gençlerden İdil Bayar olay yerinde, Şükrü Şankaya Anadolu Lisesi’nden yeni mezun olan Beyza Varol da kaldırıldığı Uludağ Üniversitesi Tıp Fakültesi Hastanesi’nde yaşamını yitirdi.  Kazada yaralanan Aytuğ Bayar, Basri Yılmaz, Berfo Çakır, Beril Varol ve Tolga Şahin ise çevredeki hastanelerde tedavi altına alındı. Kazada ölen İdil Bayar ve yaralanan Aytuğ Bayar’ın kuzen oldukları öğrenildi. AİLELER CENAZELERİ TESLİM ALDI Bursa’da dün girdikleri LYS sonrası gittikleri eğlence dönüşü Mudanya’da geçirdikleri trafik kazasında yaşamlarını yitiren İdil Bayar ve Beyza Varol’un Bursa Adli Tıp Kurumu’na getirilen cenazeleri, aileleri tarafından teslim alındı. İdil Bayar’ın Orhangazi’nin Gürle, Beyza Varol’un da Gemlik’in Küçük Kumla Mahallesi’nde toprağa verileceği bildirildi. Kazada yaşamını yitiren Özel Tan Anadolu Lisesi 12’nci sınıfa geçen İdil Bayar’ın, bu yıl LYS’ye giren kuzeni Aytuğ Bayar ile eğlenceye gittiği öğrenildi. Kazada yaşamını yitiren Beyza Varol’un kardeşi Beril Varol ve diğer yaralıların Uludağ Üniversitesi, Çekir ve Muradiye Devlet Hastanelerindeki tedavileri ise devam ediyor. Kazayla ilgili soruşturma sürdürülüyor. KORKUNÇ BİLANÇO: 5 AYDA 1204 KİŞİ TRAFİK KAZALARINDA CAN VERDİ   TEK TEK BELİRLENDİ… EN TEHLİKELİSİ  "
			},
			{
				name : "  İçerik 31  ",
				sentence : "Törene Başbakan Yardımcısı Bülent Arınç, CHP Genel Başkanı Kemal Kılıçdaroğlu, MHP Genel Başkanı Devlet Bahçeli, TBMM Başkanvekili Ayşe Nur Bahçekapılı, çok sayıda milletvekili ve Sökmenoğlu'nun ailesi katıldı. Sökmenoğlu'nun cenazesi toprağa verilmek üzere Hatay'a gönderildi.    "
			},
			{
				name : "  İçerik 32  ",
				sentence : "Yüksel’in savcılık döneminde avukatları tehdit eden uygulamalara imza attığını belirten Çağdaş Hukukçular Derneği, Yüksel’e verilen avukatlık ruhsatının iptali için her türlü girişimde bulunacağını açıkladı. Yüksel ise eleştirilere, “İsteyen istediğini söylesin” yanıtı verdi. Ankara Baro Başkanı Sema Aksoy ise “Avukatlık kanuna kapsamında gerekli koşulları taşıyordu” dedi.              "
			},
			{
				name : "  İçerik 33  ",
				sentence : "TBMM Başkanvekili Güldal Mumcu, Muş Milletvekili Sakık’ın 1 Haziran’daki yerel seçimde belediye başkanı seçilip mazbatasını aldığını hatırlatan ve Mahalli İdareler Kanunu gereği TBMM Başkanlığı’na sunduğu yazıyı okuttu. Mumcu, “Sayın milletvekilleri, bu tercih karşısında Sakık’ın TBMM üyeliği, belediye başkanlığını tercih ederek göreve başladığı tarih itibarıyla,  kendiliğinden sona ermiş bulunmaktadır” dedi. Sakık, belediye başkanı olarak Meclis’ten ayrılan 10. vekil oldu. Yasama dokunulmazlığı sona eren Sakık hakkında TBMM Anayasa Adalet Karma Komisyonu’nda bulunan 24 dokunulmazlık dosyası, Başbakanlık aracılığıyla Adalet Bakanlığı’na iletilecek.    "
			},
			{
				name : "  İçerik 34  ",
				sentence : "“Sayın Erdoğan sen ki, Kenan Evren’in ve 12 Eylül cuntasının kucağında pışpışlanan, muhtıralardan süt emen, 12 yıldır Türkiye’ye kan ağlatan bildik bir despotsun” diyen Bahçeli, özetle şunları söyledi: PINARHİSAR’I MUMLA ARAYACAK Kenan Evren ile Tahsin Şahinkaya’nın yargılanmasından çıkan netice bizleri nispeten memnun etmiştir. Birisi 89, diğeri 97 yaşında 2 darbeci generalin cezalandırılmasıyla 12 Eylül aklanmayacaktır. 12 Eylül’ü sorgulayacaksak sadece 2 yaşlı darbeciye güç gösterisi yapmanın akıl kârı olmadığını bilmemiz gerekir. 12 Eylül 1980’den sonra çıkarılan yasaları, ülke yönetiminde bulunanları ne yapacağız? Ülkücülere reva görülen zalimlikleri Başbakan nereden bilecektir? Başbakan’ın anlayabilmesi ve vicdanında hissetmesi için önce adam olması, kalbi vatan ve bayrak aşkıyla çarpması gerekecektir. 34 yıl sonra darbeciler şeklen cezalandırılıyorsa, 10 yıllar geçse de bir gün gelecek 17-25 Aralık’tan dolayı Başbakan adaletin önüne çıkacak ve yaptıklarının bedelini ödeyecektir. Başbakan, Pınarhisar’daki 3-5 ayını bile mumla arayacaktır. BEDEL ÖDEMEDE SIRAYI SAVDIK AKP hükümeti Türk askerini darbeci göstermiştir. En son olarak Balyoz davası, AKP’nin başında patlamıştır. AKP’nin suflörlüğünde hukuku linç eden hakim ve savcılar tarihe kara bir leke olarak geçmişlerdir. Merak ediyoruz ki, uyduruk delilerle hüküm ihdas eden hakim ve savcılar şimdi ne yapacaklar, nereye sığınacaklardır? Dahası cezaevlerinde vefat edenleri kim geri getirebilecektir? Ailelerin dramını Başbakan ve kolkola TSK’ya kumpas kurduğu ahlaksızlar nasıl telafi edecektir? İyi ki Anayasa Mahkemesi vardır, iyi ki hâlâ vicdanını satmayan yargıçlar bu ülkede görev başındadır. TEMELİMİZDE HARAM LOKMA YOK Türk siyasetinin güzide partileri geçen hafta CHP’nin gündeme getirdiği Sayın Prof. Dr. Ekmeleddin İhsanoğlu etrafında toplanmaya başlamıştır. Çatı adayımızı, ‘Pensilvanya projesi’ yakıştırmasıyla küçültmeye çalışan omurgasızlar çıkmıştır. Tabii bu çevreler, icazeti ve ilhamı Recep Tayyip Erdoğan’dan almışlardır. Sayın İhsanoğlu ne Pensilvanya imalatı, ne Washington kurgusu olmayıp, büyük Türk Milleti’nin adayıdır. Bizim çatımızın temeli de binası da vardır. Çözemediğimiz Başbakan’ın temelinde nelerin olduğudur. Bizim temelimizde"
			},
			{
				name : "  İçerik 35  ",
				sentence : "CHP Genel Başkanı Kemal Kılıçdaroğlu, 4 eski bakan hakkında kurulmasına karar verilen soruşturma komisyonuna Ak Parti’nin üye vermemesini eleştirerek, Başbakan Erdoğan’ın olası cumhurbaşkanlığı adaylığını değerlendirdi. Kılıçdaroğlu, “Sen namuslu bir adamsan, kul hakkı yemediysen, neden bu komisyonu kurmuyorsun? Şimdi de cumhurbaşkanlığına soyunuyor beyefendi, malı daha büyük götürmek için” dedi. CHP grup toplantısında konuşan Kılıçdaroğlu, özetle şunları söyledi: Anayasa Mahkemesi’NE ŞÜKRAN     ‘Ankara’da hâkimler var’ dedirten Anayasa Mahkemesi’ne şükranlarımı sunuyorum. Dava konusunda çok şey söyledik, onlar da bize ‘Ergenekoncusunuz, darbecisiniz’ dediler. Oysa darbelerde en çok mağdur olan biziz, onlar değil. Yazık, günah değil mi insanlara. İntihar edenler, ölenler oldu. Acı ve gözyaşı vardı. Biz bunu unutacak mıyız? Mahkeme bu kararı verdi, diye geriye dönüp kendimizi, vicdanımızı sorgulamayacak mıyız? ZAYTUNGCULARA ÇAĞRI Erdoğan, 15 Temmuz 2008’de Ergenekon ve Balyoz davaları için, ‘Bir anlamda savcılık ise evet ben bu davaların savcısıyım’ dedi. Bu ifadeyi eleştirmiştim. Aradan 3 yıl geçti, 2011 yılında Erdoğan, ‘Hiçbir yerde bu ifadeyi kullanmış değilim. Benim kullandığım ifade şudur: ‘Ben milletin avukatıyım..’ dedi. Pes! ‘Savcıyım’ diyorsun, sonra dönüp ‘Ben bunu söylemedim’ diyorsun. Vallahi zaytungcular bunu değerlendirecektir. HIRSIZLARA SAYGIM YOK Erdoğan, Türkiye Cumhuriyeti tarihinde rüşvet pazarlığı yapan bir Başbakan’dır. Bilal Erdoğan, babasını arıyor, ‘Dün Sıtkı Bey geldi’ diyor. ‘Bir türlü doğru bir şekilde transfer işlemini yapamadığını, bir 10 milyon dolar olduğunu’ söylerken, Erdoğan ‘Sakın alma. Bize ne sözü verdiyse onu getirecekse getirsin, getirmeyecekse gerek yok. Başkaları getiriyor da o niye getiremiyor?’ diyor. Böyle birisinin başbakanlık koltuğunda oturması bu ülke için büyük bir ayıptır. Her siyasi görüşe benim saygım vardır. Ama kimse kusura bakmasın, hırsızlara saygım yoktur. Kul hakkı yiyene saygım yoktur. Bu Sıtkı Bey, Wikileaks belgelerinde adı geçen Sıtkı Ayan. Büyük işler çeviren birisi.          Silah sevkinin belgelerini açıklayacağız                     “(IŞİD’in Musul’u işgaliyle başlayan sürece ilişkin) Merkezi Irak yönetimiyle, bölgesel Kürt yönetimiyle görüşün ve oradaki Türklerin güvence altına alınması için çalışın. BM’yi harekete geçirin. İnsani yardımlar için gerekirse Zaho’da merkez kurun. Silah değil insani yardım yapmalıyız. İnsanları kurşuna diziyorlar, kalbini, göğsünü çıkarıyorlar, çiğ çiğ yiyorlar"
			},
			{
				name : "  İçerik 36  ",
				sentence : "Alan önce MHP’nin dünkü grup toplantısına katıldı. Ardından MHP Genel Başkanı Devlet Bahçeli ile birlikte Genel Kurul’a girdi. Alan’ı eşi Nevin ile kızı Pelin Çetin de dinleyici locasından izledi. Aileye, MHP MYK üyesi Erkan Haberal eşlik etti. Meclis Başkanvekili Güldal Mumcu, Alan’ı kürsüye davet ederken “Kararın gecikmesinden dolayı üzüntümü belirtiyorum” dedi. Alan, yeminini ettikten sonra ilk tebrik Bahçeli’den geldi. Yemin sırasında HDP’liler salonda yer almadı. Alan’ı MHP, CHP gruplarının tamamı ve AK Partili birkaç vekil alkışladı. Ergenekon davasından tahliye olan CHP’li vekiller Mustafa Balbay ve Mehmet Haberal da Alan’ı sarılarak kutladı.     "
			},
			{
				name : "  İçerik 37  ",
				sentence : "İftiralarla dolu bir konuşma. Kalkıp evladıma Hazine arazilerinin tahsisinden bahsediyor. Terör örgütünün başıyla aynı sofraya oturup oturmamaktan bahsediyor. Ey Bahçeli, bunları ispat edemezsen sen alçaksın adisin” sözleriyle çıkıştı. Dünkü Ak Parti grup toplantısında konuşan Erdoğan, özetle şunları söyledi: “Ağzından salyalar akıyor. Bu adam siyasette çırak bile olamadı. Varlığı MHP teşkilatı için tehlikedir. İkide bir ayakkabı kutularıyla konuşup duranlara sesleniyorum. Milyarlarla ne avro ne dolar ayakkabı kutularına sığmaz. O ancak sizin kasalarınıza sığar. Ben davalarımı yine açacağım. Başbakan’a ‘köksüz, despot’ diyen bir adam bunun hesabını verecektir. (Bahçeli’nin ‘Pınarhisar’da yattığın günlere arayacaksın’ sözlerini yanıtlarken dil sürçmesi üzerine) Ey Bahçeli, o Pensilvanya’daki yatışımın nedeni benim asilliğimin ifadesidir. Sen bir defa kendine bak. Pınarhisar günleri senin düşündüğün ya da düşlediğin günler değil zaten. PROTOKOL MAKAMI OLMAYACAK Artık seçilmiş hükümetin, yani milli iradenin karşısında devlet iradesini temsil eden bir cumhurbaşkanlığı makamı olmayacak. Seçilmiş hükümet ile seçilmiş cumhurbaşkanı, birlikte ülkeyi idare edecek.”     Darbecileri kutlamayız BAŞBAKAN Tayyip Erdoğan, dün öğle yemeğinde AB Büyükelçileri’ne şunları söyledi:  m Dost acı söyler, Mısır’da sandıkla gelmiş yüzde 54 oy almış bir yönetimi, askeri darbeyle devirmişlerdir. Batılı dostlarım ve dünya buna darbe diyememiştir. Biz darbe yönetimini tebrik edemeyiz. m Diyarbakır’da evlatları kaçırılan anneler oturma eylemi düzenlediler. Bunların içinde 13’ten tutun 25 yaşına kadar gençler var. Siz büyükelçilere zahmet olmazsa bir Diyarbakır ziyaretini de bunun için yapın. AK Parti Köşk adayı 1 Temmuz’da Başbakan Erdoğan, AK Parti’nin cumhurbaşkanı adayının açıklanmasını, 1 Temmuz’da ATO Congresium’da yapacaklarını duyurdu. Adayı Genel Başkan Vekili sıfatıyla Mehmet Ali Şahin açıklayacak. Daha sonra Meclis’e geçilecek ve grup başkan vekilleri TBMM Başkanlığı’na adayları için başvuracak. - Nuray BABACAN / ANKARA  "
			},
			{
				name : "  İçerik 38  ",
				sentence : "Erdoğan, kendisinden yardım isteyen annelere, geri dönüşleri sağlamaya yönelik bir yasal hazırlık içinde olduklarını söyledi. “Kardeşin kardeşi vurmasının önü kesilmeli” diyen Erdoğan, sorunun çözümü için ellerinden geleni yaptıklarını kaydetti. Erdoğan, “Hiç birinizi mağdur etmek istemem ama elimde de sihirli bir değnek yok” diye konuştu. Anneler daha sonra Cumhurbaşkanı Abdullah Gül tarafından, Köşk’te kabul edildi.  "
			},
			{
				name : "  İçerik 39  ",
				sentence : "Öğrencilerin bu sınavlardan aldığı puanlar hem karnelerinde kullanılarak, yıl sonu başarı puanlarını etkileyecek hem de liselere yerleştirmelerde kullanılacak. Ancak, yapılan başvuru üzerine Ankara 13’üncü İdare Mahkemesi, kasım ayında uygulanan fen ve teknoloji testindeki bir soruyu iptal etti. Milli Eğitim Bakanlığı ise, mahkemeye itiraz etti. İtiraz kabul edilmezse 1 milyon 270 bin öğrencinin karnesinin de dahil olduğu sınav sonuçlarının yeniden hesaplanması gündeme gelebilir.  "
			},
			{
				name : "  İçerik 40  ",
				sentence : "Dini nikâhla yaşadığı eşi Sarkis Sıtkı Eken ile 60 yıl sonra geçen ay kilisede resmi nikâh kıyan Beyzar Alata için 21 yıldır yaşadığı Süryani Meryem Ana Kilisesi’nde tören yapıldı. Törene, Diyarbakır Büyükşehir Belediye Başkan Vekili Fırat Anlı, Sur Belediye Başkanı Seyit Narin, çok sayıda sivil toplum kuruluşu temsilcisi, yakınları katıldı.    "
			},
			{
				name : "  İçerik 41  ",
				sentence : "AİLE ve Sosyal Politikalar Bakanlığı, Türkiye’de kullanımı giderek artan ve bağımlılık yaşı giderek düşen uyuşturucu maddelere karşı çocukların korunması için harekete geçti. Ergenlik çağında veya uyuşturucu bağımlısı çocukları bulunan ailelere önemli uyarı ve önerilerin sıralandığı kitapçıkta özetle şunlar vurgulandı: SOKAK DİLİNE DİKKAT  Ergenlikte okul performansında bozulma, davranışlarda belirgin değişim, ilgi alanlarına karşı arzuyu kaybetme, sigara kullanma, sokak dili ile konuşma, yasadışı işlere karışma, güvenli olmayan cinsel deneyimler gibi riskli davranışlar vardır. Bu davranışların artması uyuşturucu madde bağımlılığını da beraberinde getirebilir. Bu gibi sorunlar görülmesi halinde, ailenin de davranış şeklini değiştirmesi gerekir. Ergenler için uyuşturucu madde kullanımı, özerkliğin sembolü olabilir. Merak, arkadaş baskısı, kişisel yatkınlık, sorunlarla başetme yöntemi olarak, kendini kanıtlamak, farklı görünmek için uyuşturucu madde kullanımı başlayabilir. Ancak güçlü ve pozitif aile bağları, ebeveynlerin çocuklarından haberdar olması kişileri uyuşturucudan uzak tutar. SUÇLAMAYIN, KONUŞUN Çocuğunuzun uyuşturucu kullandığından şüpheleniyorsanız, öncelikle suçlamayın, süreci iyi değerlendirin. Çocuğunuzu zor durumda bırakmadan öğretmenleriyle arkadaşlarıyla iletişime geçin. Birkaç kez madde kullanmış olması bağımlı olduğu anlamına gelmez. Endişelenip ani kararlar vermeyin. Çocuğunuzu kötü alışkanlıklara yönlendirmeyin. Çakmağınızı, sigaranızı, içkinizi çocuğunuzdan istemeyin. Aynı internet gruplarına üye olun. Çocuğunuza sorumluluk verin, kural koyun, iletişim kurun, ona yakın olun, suçlamayın, eleştirmeyin, karar vermesine izin verin. ÇEVRENİZİ DEĞİŞTİRİN KİTAPÇIKTA yer alan diğer öneriler de şöyle:  Çocuğunuz bir şekilde bağımlı olduysa, çevrenizi değiştirin (ev taşıma, şehir değiştirme gibi).  Çocuğunuzun olumlu yanlarını destekleyin. Sınırlarınızı belirleyin.  Çocuğunuzu anlamaya çalışın ve onunla asla tartışmayın.  Eşinizle konuşun ve mutlaka bir fikir birliğine varın. Aile olarak birbirinizle iletişim halinde olun.  İletişiminizdeki temel slogan ‘polis değil aileyiz’ olmalı. Duygularınızı ve düşüncelerinizi dürüstçe belli sınırlar içinde ifade etmeyi unutmayın.  Gerçekçi olun, sorumluluğu paylaşın. Çocuklarınızın olumlu geri bildirime ihtiyacı vardır. Çocuğunuzu destek alması için yönlendirin.  "
			},
			{
				name : "  İçerik 42  ",
				sentence : "Sepetçiler Kasrı’nda, Yeşilay Genel Merkezi’ndeki serginin açılış töreninde konuşan Bispuri, “Çalışmalarım sırasında 8 yaşındaki çocukların uyuşturucudan hayatlarını kaybettiklerini gördüm. Bu tüm dünyada yaygın bir sorun haline geldi” dedi. Fotoğraf sergisinin açılış törenine katılan İstanbul Valisi Hüseyin Avni Mutlu da, “Yeşilay’ı uyuşturucu madde bağımlılığı ile ilgili konulardaki gayreti nedeniyle yürekten kutlamak istiyorum” dedi.  "
			},
			{
				name : "  İçerik 43  ",
				sentence : "İstanbul 5’inci Ağır Ceza Mahkemesi’ndeki duruşmada son savunmasını yapan Tasalı “Öldürmek istememiştim. Pişmanım” dedi. Mahkeme heyeti, sanığın duruşmadaki iyi hali ve pişmanlığı nedeniyle cezasını müebbet hapse indirdi. Heyet, Tasalı’ya cinsel saldırı ve hırsızlık suçundan da 8 yıl 4 ay hapis verdi. Mahkeme Başkanı Tevfik Güngören ise, ‘cinsel saldırı’ suçundan beraat kararı verilmesi yönünde oy kullandı.  "
			},
			{
				name : "  İçerik 44  ",
				sentence : "Otomobili 13 yaşındaki R.P.’nin kullandığı, babası Mustafa P.’nin de yanında oturduğunu gören polisler tutanak tutarken, baba “Oğlum 3 yıldır süper araba kullanıyor. Hatta benden daha iyi kullanıyor. Samsun’dan Giresun’a kadar arabayı o sürdü” dedi. 1407 lira trafik cezası kesilen baba, “Arabayı 10 dakika sonra yine oğluma vereceğim” diye konuştu.  "
			},
			{
				name : "  İçerik 45  ",
				sentence : "HÜKÜMETİN çözüm süreci konusunda hazırladığı yeni paketin akıbeti belli oldu. 7 maddelik paket, TBMM tatile girmeden yasalaştırılacak. Pakette, çözüm sürecinde görev alan kamu görevlilerine ‘yasal güvence’ getiriliyor. Paketteki diğer önemli düzenleme ise eve dönüş hükümleri olacak. Dağdan inen PKK’lılardan örgüt yöneticisi olmayanlara rehabilitasyon olanakları sağlanacak. Hükümet temsilcileriyle, HDP ve Abdullah Öcalan arasında sürdürülen çözüm süreci görüşmelerinde gelinen son nokta ortaya çıktı. Geçen ayki görüşmelerden sonra TBMM’ye sevk edilmesi beklenen paketle ilgili farklı değerlendirmeler yapılmıştı. HDP’nin derhal istediği paket konusunda, hükümet kanadından ekim ayına kalabileceği yorumları gelmişti. Ancak son haftada artan çalışmaların ardından, 7 maddelik bir paketin hazırlıkları tamamlandı. Başbakan Tayyip Erdoğan’ın onayıyla, TBMM tatile girmeden önce paketin yasalaştırılması kararlaştırıldı. Çözüm süreci çalışması bakanların imzasına açıldı SÜREÇ AKTÖRLERİNE  KORUMA Pakette, çözüm süreciyle ilgili alınan kararların ilerde ‘suç’ kapsamına alınmaması için yeni görev tanımları yapıldı. Çözüm sürecinde ve terörle mücadelede alınan kararların sorumluları korunacak. İçişleri Bakanlığı’nın hazırladığı pakete göre, sürecin aktörlerinin ilerde suçlanması engellenecek. Siyasetçi, bürokrat ve askerlerin bu süreçte gerçekleştirdikleri görevin tanımı da yapıldı. TOPLUMA KAZANDIRMA Pakette, örgüt yöneticisi olmayan PKK’lılardan dağdan inmek isteyenlerin eve dönüşünü kolaylaştıran ve rehabilitasyonunu sağlayan hükümler de bulunuyor. Özellikle çocukları dağa kaçırılan ailelerin kampanyalarından sonra silahlı çatışmaya katılmayan gençlerin eve dönmesi ve sorumlu tutulmamalarıyla ilgili hükümler yer alacak. Hasta mahkûmlarla ilgili olarak ise Adli Tıp Kurumu sürecinin hızlandırılması gibi maddelerin de pakette yer alacağı belirtiliyor. HDP’nin beklediği ‘Avrupa Yerel Yönetimler Özerklik Şartnamesi’ çerçevesindeki düzenlemelerin ise pakette yer almadığı vurgulandı. TATİL ERTELENİYOR Meclis’in resmi olarak 1 Temmuz’da tatile girmesi gerekiyordu. Ancak çözüm sürecine ilişkin yasal düzenlemenin Meclis’ten çıkarılması isteği, tatilin ertelenmesini zorunlu kıldı. Bu nedenle Meclis, torba tasarı ve çözüm süreci paketi yasalaşıncaya kadar çalışmalarına devam edecek. Bunun da temmuz ortasını bulması bekleniyor."
			},
			{
				name : "  İçerik 46  ",
				sentence : "Demokratik Özgür Kadın Hareketi (DÖKH) öncülüğünde ’Kadınlar önderliği ve özgürlüğü için eylemde’ adı altında düzenlenen mitinge HDP Grup Başkanvekili Pervin Buldan, milletvekilleri Faysal Sarıyıldız, Selma Irmak ile çok sayıda vatandaş katıldı. Pervin Buldan, İmralı’da Abdullah Öcalan’a selam göndererek başladığı konuşmasında, şöyle dedi: \"Sevgili kadınlar bugün sayın Öcalan’ın özgürlüğü için bir araya geldik. Yıllardır bu meydanlarda, alanlarda Öcalan’ın özgürlüğünü isteyen kadınlarımız ve gençlerimize selam olsun. Sayın Öcalan yaptığımız her toplantıda özellikle Botanlı kadınlardan bahsediyor. Kadının özgürlüğü benim için Kürt meselesinden toprak meselesinden daha önemlidir diyen sayın Öcalan bugün bu meydanda. Sayın Öcalan’ın özgürlüğünü isteyen kadınlardan bir kez daha memnuniyeti ifade edecektir. 15 yıldır İmralı cezaevinde olmasına rağmen bir halkın özgürlüğünün gerçekleşmesi için mücadele ediyor. Ama artık sıra bizde. Sayın Öcalan özgür olana kadar bu meydanlardan, alanlardan Öcalan’ın özgürlüğünü istemekten vazgeçmeyeceğiz. Sıra bizde diyoruz. Senin özgürlüğünü elde edene kadar Kürt halkı olarak evimizde oturmayacağız. Birinci koşul olarak sayın Öcalan’ın koşulları hemen iyileştirilmeli, şartları düzeltilmeli ve bu süreç en kısa zamanda nihai bir sonuca kavuşmalıdır.\" BAYGINLIK GEÇİRDİ Pervin Buldan, konuşmasını sürdürürken, aniden fenalaşarak baygınlık geçirdi. Buldan’a sağlık görevlileri müdahalede bulundu. Bir süre dinlenen Buldan, daha sonra ’Silopi seninle gurur duyuyor’ sloganları altında konuşmasına devam etti. Tansiyonunun düşmesi sonucu bayıldığını söyleyen Pervin Buldan, şöyle dedi: \"Yine kalktım sizlerle birlikteyim. Bugün bu coğrafyada eğer kan akmıyorsa, eğer çocuklarımız ölmüyorsa, gencecik fidanlarımız yaşamını yitirmiyorsa, bu sayın Öcalan’ın başlatmış olduğu bu süreç sayesindedir. Ama tabi ki 1.5 yıldır bu sürecin tek bir kazanımı olmuştur. O da Öcalan’ın başlatmış olduğu bu süreçle başlayan çatışmasızlık sürecinde hiç kimsenin yaşamını yitirmemesidir. Tabi ki bizler için bu çok önemlidir. Çocuklarımızın yaşamını yitirmemesi bizler için en büyük kazanımdır. Ama bu süreç sadece çatışmasızlık sürecinin korunması ile ilerleyecek bir süreç değildir. AKP hükümetinin artık bir gün bile beklemeden hemen acilen somut adımlar atması gerekmektedir. Botan halkına karşı AKP hükümeti suçludur. Burada Roboski’de 34 insanımız savaş uçakları ile paramparça edildi. Ve onların katilleri yargılanmadı, aksine aklandı. Botan halkı Roboski’de yaşamını yitiren 34 canımızın katilleri yargılanmadan bu sürece barış süreci olarak bakamaz. AKP hükümetinin bunu dikkate alması gerekir. AKP’nin bunu dikkate alarak 34 canımızı katledenlerin yargılaması gerekir. İşte o zaman Botan topraklarına barış gelecektir.\" Buldan’ın konuşması boyunca sahnede duran diğer milletvekili ve görevliler, destek olmaya çalıştı. Pervin Buldan, daha sonra gittiği Silopi Devlet Hastanesi’nde gördüğü tedavinin ardından ayrıldı.    "
			},
			{
				name : "  İçerik 47  ",
				sentence : "İZMİR'de 4 yıl önce Buca Kız Yetiştirme Yurdu’ndan kaçan ve nüfus kayıtlarına göre 15 yaşında olan N.U., C.Y.T.’nin (32) kendisine tecavüz edip, bekâretini bozduğu iddiasıyla şikâyetçi oldu. Adli Tıp’ta yapılan muayanede N.U.’nun bekâretinin bozulmadığı, hastane raporunda da, ruh sağlığının bozulduğu raporu verildi. Gözaltına alınan C.Y.T., tutuksuz yargılanmak üzere serbest bırakıldı. Cumhuriyet Savcısı, C.Y.T. hakkında Ağır Ceza Mahkemesi'nde 25 yıl hapis cezası istemiyle dava açtı. ‘NAR SUYU DÖKTÜN’ N.U., mahkemedeki ifadesinde “Birlikte olduk. Kızlığımı bozduğunu söyleyip tartıştık. Kendisi ‘Ben senin kızlığını bozmadım. Sen nar suyunu yatağa’ döktün diye çıkıştı. Parka götürüp bıraktı. Ben de şikâyetçi oldum” dedi. 15 DEĞİL 19’MUŞ Suçlamaları kabul etmeyen sanığın avukatı Adnan Özdemir, N.U’nun olay tarihinde yaşının 19 olduğunu öne sürdü. Hastanede yapılan kontrolde N.U.’nun kemik yaşının 19 olduğu doğrulanırken, çarşaftaki lekenin de nar suyu olduğu belirtildi. Mahkeme heyeti, mağdurenin yaşının düzeltilmesine, sanık C.Y.T.’ninn beraatine karar verdi.  "
			},
			{
				name : "  İçerik 48  ",
				sentence : "Avusturya’nın Türkiye Büyükelçisi Klaus Wölfer, 25 Haziran tarihinde mimar Brigitte Weber’e Avusturya Cumhuriyeti Altın Liyakat Nişanı’nı tevdi etti. Avusturya İstanbul Başkonsolosluğunun bulunduğu Yeniköy Sarayında gerçekleştirilen törene mimar çevreleri ile ticaret hayatından tanınmış kişiler katıldı."
			},
			{
				name : "  İçerik 49  ",
				sentence : "ABD Savunma Bakanlığı (Pentagon) Sözcüsü John Kirby, düzenlediği basın toplantısında, Bağdat'ta özel operasyonlar merkezi kurmaya yardımcı olmakla görevlendirilen 90 civarında askerden oluşan grubun Bağdat'a vardığını belirterek, bu grubun daha önce ABD'nin Bağdat Büyükelçiliği'nde görevlendirilen yaklaşık 40 kişilik personele katılacağını ifade etti."
			},
			{
				name : "  İçerik 50  ",
				sentence : "Alınan bilgiye göre, Suudi Arabistan'ın başkenti Riyad kentinden 178 yolcusu ile havalanan uçağa, Pakistan'ın Afganistan sınırı yakınlarındaki kenti Peşaver'e inişi sırasında kimliği henüz belirlenemeyen kişilerce ateş açıldı."
			},
			{
				name : "  İçerik 51  ",
				sentence : "Ulusal güvenlik şefi Monte Alejandro Rubido, Arellano'nun dün Tijuana kentinde, Cesea semtinde 2014 FIFA Dünya Kupası çerçevesinde oynanan Meksika-Hırvatistan maçını izlediği sırada yakalandığını açıkladı."
			},
			{
				name : "  İçerik 52  ",
				sentence : "Irak Başbakanı Nuri el Maliki, ülkedeki IŞİD tehdidine karşı acilen bir ulusal geçiş hükümeti kurulmasının söz konusu olmadığını söyledi."
			},
			{
				name : "  İçerik 53  ",
				sentence : "Anadolu Ajansı'nın haberine göre, NATO Dışişleri Bakanları Toplantısı'nın Brüksel’deki sabah oturumları sonrasında düzenlenen basın toplantısında konuşan Anders Fogh Rasmussen, Irak konusunun Türkiye tarafından gündeme getirildiğini belirterek, \"Sınırlarına yakın bölgedeki durumdan çok endişeliler ve bunu gündeme getirmeleri konusunda iyi nedenleri var\" dedi."
			},
			{
				name : "  İçerik 54  ",
				sentence : "Toplantıya katılan Dışişleri Bakanı Ahmet Davutoğlu, ABD Dışişleri Bakanı John Kerry ile bir araya geldi. Görüşmede Kerry, önümüzdeki aylarda Kıbrıs'ı ziyaret etmeyi planladığını açıkladı."
			},
			{
				name : "  İçerik 55  ",
				sentence : "Libya'da halk, 40 yıllık Muammer Kaddafi yönetimine son veren 2011'deki halk ayaklanmasından bu yana ikinci kez parlamento seçimleri için sandığa gidiyor."
			},
			{
				name : "  İçerik 56  ",
				sentence : "Clint Eastwood'un başrolünü oynadığı unutulmaz 'Western' filmi \"İyi, Kötü, Çirkin\"in 'Çirkin' karakteri Eli Wallach, 98 yaşında hayatını kaybetti."
			},
			{
				name : "  İçerik 57  ",
				sentence : "İngiltere Kraliçesi 2'nci Elizabeth ve eşi Edinburg Dükü Phillip Kuzey İrlanda’ya yaptıkları resmi ziyaret kapsamında tüm dünyada ilgiyle izlenen Game of Thrones dizisinin Belfast şehrindeki setini ziyaret etti."
			},
			{
				name : "  İçerik 58  ",
				sentence : "ABD Başkanı Barack Obama, geçen hafta yaptığı açıklamada, Irak'a sayıları 300'e kadar Amerikalı askeri danışman göndereceklerini ve Bağdat ile kuzey Irak'ta ortak operasyon merkezleri oluşturacaklarını duyurmuştu."
			},
			{
				name : "  İçerik 59  ",
				sentence : "BEŞİKTAŞ, yeni sezon için dün topbaşı yaptı. Takım yeni umutlarla Ümraniye’de toplanırken teknik direktör Slaven Bilic de yeni imajıyla dikkat çekti."
			},
			{
				name : "  İçerik 60  ",
				sentence : "2014-2015 sezonunu açmasına sadece 13 gün kalan Galatasaray’da her şey Florya’nın yeni patronunu bekliyor. Thomas Tuchel’in de Cimbom’a olumsuz yanıt vermesinin ardından, bir yanda çözülmeyi bekleyen problemler, diğer yanda bir türlü netleşmeyen teknik direktör konusu dikkat çekiyor."
			},
			{
				name : "  İçerik 61  ",
				sentence : "FENERBAHÇE Başkanı Aziz Yıldırım için dün bambaşka bir gündü… Tam 3 yıl sonra ilk kez endişesiz ve rahat uyandı…"
			},
			{
				name : "  İçerik 62  ",
				sentence : "UEFA’da soruşturma geçiren TFF, mahkeme kararını çevirtip, UEFA’ya gönderecek. Böylece, olası bir ceza alma ihtimalinin de önüne geçilmeye çalışılacak."
			},
			{
				name : "  İçerik 63  ",
				sentence : "2014 FIFA Dünya Kupası C Grubu maçının 85. dakikasında oyuna giren, bir dönem Galatasaray'ın da formasını giyen 43 yaşındaki Kolombiyalı file bekçisi Faryd Mondragon, \"Dünya Kupası tarihinde forma giyen en yaşlı futbolcu\" oldu."
			},
			{
				name : "  İçerik 64  ",
				sentence : "Kolombiya'nın Japonya'yı 4-1 yendiği C Grubu son maçının 85. dakikasında teknik direktör Jose Pekerman'ın sahaya sürdüğü Mondragon, 43 yaş 3 gün ile \"Dünya Kupası tarihinde forma giyen en yaşlı futbolcu\" unvanını, ABD 1994'te 42 yaş 1 ay 8 günlükken Rusya'ya karşı oynayan Kamerunlu forvet oyuncusu Roger Milla'nın elinden aldı."
			},
			{
				name : "  İçerik 65  ",
				sentence : "NBA'de sezonu Doğu Konferansı'nı şampiyon olarak tamamlayan fakat final serisinde San Antonio Spurs'e kaybeden Miami Heat'de 'Kral' lakaplı LeBron James takımı ile süren sözleşmesini feshetme kararı aldı."
			},
			{
				name : "  İçerik 66  ",
				sentence : "Sırbistan Basketbol Ligi Play-Off serisinde iki ezeli rakip Partizan ve Kızılyıldız takımları arasında oynanan final maçının sonunda ilginç bir olay yaşandı."
			},
			{
				name : "  İçerik 67  ",
				sentence : "KARAAĞAÇSPOR alt yapısında yetişen Selçuk İnan, memleketi İskenderun’da Galatasaray Futbol Okulu sporcularıyla buluştu."
			},
			{
				name : "  İçerik 68  ",
				sentence : "SON olarak Kayseri Erciyes’i çalıştıran Hikmet Karaman, “Büyük kulüplerin Türk hocalara fazla önem vermesi gerekiyor. Galatasaray, 4. yıldızdan bahsediyor."
			},
			{
				name : "  İçerik 69  ",
				sentence : "Manisa'nın Soma İlçesi'nde 301 işçinin hayatını kaybettiği maden faciasının ardından ilçeye en anlamlı ziyaretlerden birini Şili'de 2010 yılında yaşanan kazada yeraltında 69 gün kalan madenci Luis Urzula ile kurtarma ekibindeki Rodrigo Reveco gerçekleştirdi."
			},
			{
				name : "  İçerik 70  ",
				sentence : "İlk olarak İstanbul'da çeşitli temaslarda bulunan dün de Ankara'da TBMM'de hem muhalefet partilerinin temsilcileri hem de Soma'daki kazayı araştırmak için kurulan Meclis Araştırma Komisyonu'nun temsilcileriyle görüşmeler yaptı."
			},
			{
				name : "  İçerik 71  ",
				sentence : "ANADOLU Grubu’nun kurucularından işadamı İzzet Özilhan’ın cenazesi İstanbul’da toprağa verildi. 94 yaşında hayata veda eden Özilhan’ın cenaze töreni iş, siyaset dünyasından çok sayıda ismi bir araya getirdi. İzzet Özilhan için Ataşehir’deki Mimar Sinan Camii’nde düzenlenen cenaze töreninde taziyeleri oğlu Anadolu Grubu Yönetim Kurulu Başkanı Tuncay Özilhan, kızı Tülay Özilhan Aksoy, gelini Emine Özilhan ve torunları kabul etti."
			},
			{
				name : "  İçerik 72  ",
				sentence : "Yandex Türkiye CEO’su Mehmet Ali Yalçındağ, Fenerbahçe Spor Kulübü Başkanı Aziz Yıldırım, siyaset dünyasından  İlhan Kesici, Hüsamettin Özkan, Işın Çelebi, iş adamları Ethem Sancak, Ahmet Nazif Zorlu, Anadolu Efes Basketbol takımından bazı oyuncular, şirket çalışanları ve iş dünyasından çok sayıda isim katıldı."
			},
			{
				name : "  İçerik 73  ",
				sentence : "CHP Genel Başkanı Kemal Kılıçdaroğlu, Mustafa Sarıgül, Galatasaray Kulübü Başkanı Ünal Aysal, Beşiktaş Kulübü Başkanı Fikret Orman, Bedrettin Dalan çelenk gönderenler arasında yer aldı."
			},
			{
				name : "  İçerik 74  ",
				sentence : "Mustafa Koç, Aziz Yıldırım, Bülent Eczacıbaşı ve Güler Sabancı başta olmak üzere iş, spor ve siyaset dünyasından birçok kişi İzzet Özilhan’ın cenaze törenine katıldı."
			},
			{
				name : "  İçerik 75  ",
				sentence : "Merkez Bankası Başkanı Erdem Başçı dün 0.75’lik indirimle orta yolu buldu. Ancak hükümet cephesinde eleştirilerin sürmesi beklenirken ilk tepki Twitter üzerinden Ekonomi Bakanı Nihat Zeybekci'den geldi. Zeybekci Twitter'dan Merkez Bankası'nı adeta bombaladı."
			},
			{
				name : "  İçerik 76  ",
				sentence : "Manisa'nın Soma İlçesi'ndeki maden faciasında hayatını kaybeden 301 işçi arasında yer alan Erkan Doğdu'nun ailesi adına Avukat Cihan Türsen, Enerji ve Tabi Kaynaklar Bakanlığı ile Çalışma ve Sosyal Güvenlik Bakanlığı'na 375 bin liralık manevi tazminat davası açmaya hazırlanıyor."
			},
			{
				name : "  İçerik 77  ",
				sentence : "Birleşik Krallık İstanbul Başkonsolosu ve Ticaret ve Yatırım Ajansı Orta Asya, Güney Kafkaslar ve Türkiye Genel Direktörü Leigh Turner, Türkiye’deki genç teknoloji şirketleri arasında gerçekleştirilecek yarışmaya katılanların, İngiltere’de kendilerine yeni fırsat pencereleri aralama şansını yakalayabileceklerini açıkladı."
			},
			{
				name : "  İçerik 78  ",
				sentence : "CHP Zonguldak Milletvekili Ali İhsan Köktürk Çalışma ve Sosyal Güvenlik Bakanı Faruk Çelik'in yanıtlaması için soru önergesi verdi. Önergede \"Emeklilere 300 TL'den az olmamak üzere ikramiye verecek misiniz? sorusu yer aldı."
			},
			{
				name : "  İçerik 79  ",
				sentence : "CHP İstanbul Milletvekili Sezgin Tanrıkulu The Skunk Riot Copter isimli uçan TOMA olarak bilinen aracın Türkiye tarafından alınıp alınmadığını İçişleri Bakanı Efkan Ala'ya sordu. Söz konusu insansız hava aracı havadan biber gazı atabiliyor."
			},
			{
				name : "  İçerik 80  ",
				sentence : "Davul ve zurna eşliğinde Şişli ve Beşiktaş’ta bulunan 4 özel bankanın genel müdürlükleri önüne ellerinde salladıkları iç çamaşırları ile gelen grup eylem yaptı."
			},
			{
				name : "  İçerik 81  ",
				sentence : "Dövizzedeler adına Kadir Kocanoğlu bir açıklama yaptı. Kocanoğlu, \"Buradan Başbakanımıza sesleniyorum, bu bankaların söylediklerine gelmeyin Başbakanım. Biz döviz mağdurları sana inanıyor. Gerekeni yapacağınıza adım gibi eminim. Biz bu işe çözüm istiyoruz. Başbakanımız Recep Tayyip Erdoğan bu sorunu çözecek ben buna inanıyorum\" dedi."
			},
			{
				name : "  İçerik 82  ",
				sentence : "Hükümet, HDP ve Abdullah Öcalan arasında sürdürülen çözüm süreci görüşmelerinde gelinen son nokta sonucunda 7 maddelik bir paket ortaya çıktı. Başbakan Erdoğan’ın onayıyla Meclis tatile girmeden önce yasalaştırılacak pakete göre, dağdan inen ve örgüt yöneticisi olmayan PKK’lıların rehabilitasyonu sağlanacak. Özellikle dağa kaçırılan ve silahlı çatışmaya girmeyen çocuklar sorumlu tutulmayacak."
			},
			{
				name : "  İçerik 83  ",
				sentence : "ŞIRNAK’ın Silopi İlçesi’nde mitinge katılan HDP Grup Başkanvekili Pervin Buldan, sahnede konuşma yaparken baygınlık geçirdi. Tansiyonu düştüğü belirtilen Buldan, daha sonra konuşmasına devam etti. Pervin Buldan mitingin ardından Silopi Devlet Hastanesi’nde kısa bir süre tedavi gördü."
			},
			{
				name : "  İçerik 84  ",
				sentence : "AKP hükümetinin artık bir gün bile beklemeden hemen acilen somut adımlar atması gerekmektedir. Botan halkına karşı AKP hükümeti suçludur. Burada Roboski’de 34 insanımız savaş uçakları ile paramparça edildi."
			},
			{
				name : "  İçerik 85  ",
				sentence : "Mersin Üniversitesi’nin (MEÜ) 18’inci dönem mezuniyet töreninde gerginlik yaşandı. Mersin Üniversitesi Stadyumu’nda düzenlenen mezuniyet törenine, öğrencilerin 'Soma' temalı pankart ve afişle açmaları damga vurdu."
			},
			{
				name : "  İçerik 86  ",
				sentence : "FETTULLAH Gülen Cemaati’ne yönelik soruşturmayı sürdüren Savcı Serdar Coşkun, 9 Haziran tarihinde Emniyete bir yazı göndererek, Cemaat’in mali yapılanması dahil bütün bağlantılarının açığa çıkarılması için ayrıntılı araştırma yapılması talimatı verdi."
			},
			{
				name : "  İçerik 87  ",
				sentence : "Bu araştırma ve incelemelerin yapılabilmesi için adli birimler, bakanlıklar, bağlı kuruluşlar, MİT, Genelkurmay Başkanlığı, Jandarma Genel Komutanlığı, Emniyet Genel Müdürlüğü birimleri, MASAK, SGK, BDDK, SPK, TMSF dahil üst kurumlar, devletin ülke içi ve dışındaki birimleri ile irtibata geçilip bu yapılanmanın mali finansmanının araştırılması."
			},
			{
				name : "  İçerik 88  ",
				sentence : "MHP Genel Başkanı Devlet Bahçeli, dünkü grup toplantısında Başbakan Erdoğan’ın çatı aday eleştirilerine, “Bizim temelimizde haram lokma, evlat için Hazine arazisi yağmalamak yoktur” tepkisini gösterdi."
			},
			{
				name : "  İçerik 89  ",
				sentence : "Başbakan Tayyip Erdoğan, grup toplantısında kendisine yönelik sert sözleri nedeniyle MHP Genel Başkanı Devlet Bahçeli’ye “Baştan aşağı ağzından salyalar akıyor."
			},
			{
				name : "  İçerik 90  ",
				sentence : "TBMM'de 17'nci ve 18'nci dönem Hatay, 21'nci dönem İstanbul Milletvekilliği yapan ve 4 gün önce hayatını kaybeden TBMM eski Başkanvekili Mustafa Murat Sökmenoğlu için Şeref Kapısı önünde tören düzenlendi."
			},
			{
				name : "  İçerik 91  ",
				sentence : "Törene Başbakan Yardımcısı Bülent Arınç, CHP Genel Başkanı Kemal Kılıçdaroğlu, MHP Genel Başkanı Devlet Bahçeli, TBMM Başkanvekili Ayşe Nur Bahçekapılı, çok sayıda milletvekili ve Sökmenoğlu'nun ailesi katıldı. Sökmenoğlu'nun cenazesi toprağa verilmek üzere Hatay'a gönderildi."
			},
			{
				name : "  İçerik 92  ",
				sentence : "Diyarbakır’da bilinen son Ermeni asıllı vatandaşlardan olan 87 yaşındaki Beyzar Alata hayatını kaybetti."
			},
			{
				name : "  İçerik 93  ",
				sentence : "Hatay’ın Erzin ilçesinde 20 Haziran’da annesi Çiçek Erol’un elinden tutarak yolda yürüyen 17 aylık Ela Senem Erol’un başından birden kan akmaya başladı."
			},
			{
				name : "  İçerik 94  ",
				sentence : "SAKARYA’nın Karasu İlçesi’ndeki halk plajında geçen hafta sonu cübbeli ve sarıklı kişilerin güneşlenen kadınlara \"Kapanın\" diye telkinlerde bulunarak, 'Allah ve Resulu’nun istediği hanımefendi’ başlıklı broşürler dağıttı."
			},
			{
				name : "  İçerik 95  ",
				sentence : "Başbakan Recep Tayyip Erdoğan AK Parti İl Başkanları toplantısında konuştu. Erdoğan konuşmasının ardından gazetecilere yaptığı açıklamada,Köşk seçimiyle ilgili Cumhurbaşkanı Abdullah Gül ile son bir istişare yapacağını söyledi"
			},
			{
				name : "  İçerik 96  ",
				sentence : "AFAD, TİKA ve Kızılay ekiplerimiz insani yardım taleplerini tespit ediyor ve oradaki türkmen kardeşlerimize sivil halka bunları dağıtıyor."
			},
			{
				name : "  İçerik 97  ",
				sentence : "Musul’da alıkonulan vatandaşlarımız için, Irak Kürdistan bölgesel yönetimiyle, BM ve NATO, ABD İngiltere Suudi Arabistan ve bölge ülkeleriyle yoğun irtibat halinde olduk."
			},
			{
				name : "  İçerik 98  ",
				sentence : "CHP de MHP de son derece çaresiz bir haldeler. Ne MHP de ne CHP de ilke kalmadı, sınır kalmadı, fikir zaten hiç kalmadı. Devlet Bahçeli MHP’yi aldı. CHP’nin yedeği, marjinal sol örgütlerin maymunu haline getirdi. CHP’nin genel müdürü o koltuğa oturdu. Mustafa Kemal’in partisi diyue övündüğü partiyi paçavraya çevirdi."
			},
			{
				name : "  İçerik 99  ",
				sentence : "Yapılan düğünde Arda Turan,Hakan Şükür ve Fatih Terim hazır bulundu."
			},
			{
				name : "  İçerik 100  ",
				sentence : "Mustafa Kemal Atatürk ve İsmet İnönü, Türkiye Büyük Millet Meclisi açılışı için İstanbul'dan gelerek Ankara'da kaldılar."
			},
			{
				name : "  İçerik 101  ",
				sentence : "CHP Genel Başkanı Kemal Kilicdaroglu Istanbul'dan Izmır'e geldi."
			},
			{
				name : "  İçerik 102  ",
				sentence : "CHP Genel Başkanı Kemal Kılıçdaroğlu Istanbl'dan Izmr'e geldi."
			} ];

	$scope.textArrayValue1 = $scope.textArray[0]; // first sentence

	$scope.query = {
		content : "MHP Genel Başkanı Devlet Bahçeli, cumhurbaşkanı seçimi öncesinde ortak çatı aday belirleme temasları çerçevesinde CHP Genel Başkanı Kemal Kılıçdaroğlu ile görüştü."
	};

	$scope.lookupResult = {
		displayValue : "Not yet retrieved"
	};

	$scope.selectAction = function() {
		console.log($scope.textArrayValue1.sentence);
		$scope.query.content = $scope.textArrayValue1.sentence;
	};

	$scope.performPostcodeLookup = function() {
		var url = "/duyan/DuyanServlet?" + "content=" + $scope.query.content;

		$http
				.get(url)
				.success(
						function(data) {
							console.log(data);
							$scope.lookupResult.displayValue = data.text;

							$scope.text = data.text;
							$scope.entities = data.entities;

							var txtm = $scope.text;
							for (var i = 0; i <= $scope.entities.length; i++) {
								if ($scope.entities[i] && $scope.entities[i].id) {
									var ner = $scope.text.substring(
											$scope.entities[i].start,
											$scope.entities[i].end);

									var changedNer = '<a href="'
											+ $scope.entities[i].dbpediaUri
											+ '" class="'
											+ $scope.entities[i].color
											+ '" role="button" data-toggle="popover" title="'
											+ $scope.entities[i].type + '"> '
											+ ner + '</a>';
									if ($scope.text.indexOf(ner) > -1) {
										txtm = txtm.replace(ner, changedNer);
									}
								}
							}
							$scope.text = txtm;

						});
	};
}
1. Testlerin koşacağı browser'ı seçmek için `Run/Debug Configuration dialog --> Edit Configurations... --> VM options` alanında `-Dbrowser` parametresi doldurulması gerekiyor.
    `-ea -Dbrowser=chrome`
    `-ea -Dbrowser=firefox`
    Not: Şayet bu parametre setlenmemişse default olarak testler chrome üzerinde koşacaktır.

2. Şayet testler remote'da koşmak istenirse (docker zalenium); 
        öncelikle zalenium container'larının ayağa kaldırılması

`
 # Pull docker-selenium
  docker pull elgalu/selenium

  # Pull Zalenium
  docker pull dosel/zalenium
        
  docker run --rm -ti --name zalenium -p 4444:4444 \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v /tmp/videos:/home/seluser/videos \
    --privileged dosel/zalenium start
`
        ardından ise

    `Run/Debug Configuration dialog --> Edit Configurations... --> VM options` alanında `-Dremote` parametresi doldurulması gerekiyor.
    `-ea -Dremote=true`
    `-ea -Dremote=false`
    
3. Test koşumu sırasında hata alınan yerlerde alınan ekran görüntüleri aşağıdaki folder'da tutulmakradır.
    `/Users/user/Desktop/HepsiBurada/src/main/resources/screenshots/testname.png`
    
4. Proje'nin ihtiyacı olan webdriver'ların en son sürümleri, bonie garcia paketi sayesinde şayet pc'de yok ise run time esnasında inndirilmektedir.Şayet pc'nizdeki browser 
son version değil ise browser ile webdriver uyuşmazlığından dolayı test çalışmayacaktır. Bu case'de yapılması gereken pc üzerinki browser'in versiyonuna uygun olan webdriver'in
versiyon numarasını Chrome.java 'da 15 ve 16 satırlar aşağıdaki gibi düzenlenmelidir

//        ChromeDriverManager.getInstance().setup();
        ChromeDriverManager.getInstance().version("84.0.4147.30").setup(); -> Uygun webdriver version'u burada setlenir.
        

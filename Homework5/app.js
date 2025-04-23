$(document).ready(function() {
    const apiKey = 'd10148b8965c4c4ba61143510250903'; // WeatherAPI key'inizi buraya ekleyin
    const apiUrl = 'https://api.weatherapi.com/v1/current.json?key=' + apiKey + '&q=';
    let recentCities = JSON.parse(localStorage.getItem('recentCities')) || [];

    function getWeather(city) {
        if (!city) return;

        city = city.trim().toLowerCase();

        $.ajax({
            url: apiUrl + city,
            method: 'GET',
            success: function(data) {
                const weather = `
                    <h2>${data.location.name}, ${data.location.country}</h2>
                    <p>Sıcaklık: ${data.current.temp_c}°C</p>
                    <p>Hava Durumu: ${data.current.condition.text}</p>
                    <p>Nem: ${data.current.humidity}%</p>
                    <p>Rüzgar Hızı: ${data.current.wind_kph} km/s</p>
                `;
                $('#weatherInfo').html(weather);

                if (!recentCities.includes(city)) {
                    recentCities.push(city);
                    localStorage.setItem('recentCities', JSON.stringify(recentCities));
                    updateRecentCities();
                }
            },
            error: function(xhr, status, error) {
                $('#weatherInfo').html('<p>Şehir bulunamadı. Lütfen geçerli bir şehir adı girin.</p>');
            }
        });
    }

    function updateRecentCities() {
        if (recentCities.length > 0) {
            const recentCitiesHtml = recentCities.map(city => {
                return `<p>${city.charAt(0).toUpperCase() + city.slice(1)}</p>`; 
            }).join('');
            $('#recentCities').html(recentCitiesHtml);
        }
    }

   
    $('#getWeatherBtn').click(function() {
        const city = $('#cityInput').val();
        getWeather(city);
    });

    $('#getLocationBtn').click(function() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
                const lat = position.coords.latitude;
                const lon = position.coords.longitude;
                $.ajax({
                    url: apiUrl + `${lat},${lon}`,
                    method: 'GET',
                    success: function(data) {
                        const weather = `
                            <h2>${data.location.name}, ${data.location.country}</h2>
                            <p>Sıcaklık: ${data.current.temp_c}°C</p>
                            <p>Hava Durumu: ${data.current.condition.text}</p>
                            <p>Nem: ${data.current.humidity}%</p>
                            <p>Rüzgar Hızı: ${data.current.wind_kph} km/s</p>
                        `;
                        $('#weatherInfo').html(weather);

                        const city = data.location.name.toLowerCase();
                        if (!recentCities.includes(city)) {
                            recentCities.push(city);
                            localStorage.setItem('recentCities', JSON.stringify(recentCities));
                            updateRecentCities();
                        }
                    },
                    error: function() {
                        $('#weatherInfo').html('<p>Konum bilgisi alınamadı.</p>');
                    }
                });
            });
        } else {
            $('#weatherInfo').html('<p>Tarayıcınız konum bilgisini desteklemiyor.</p>');
        }
    });

    $('#clearCitiesBtn').click(function() {
        localStorage.removeItem('recentCities'); 
        recentCities = []; 
        updateRecentCities(); 
    });

    updateRecentCities();
});

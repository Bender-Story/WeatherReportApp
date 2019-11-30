package com.rahul.weatherreportapp.network

import com.google.gson.Gson
import com.rahul.weatherreportapp.data.SearchResults
import com.rahul.weatherreportapp.data.WeatherDetailsResult
import io.reactivex.Observable
import retrofit2.mock.BehaviorDelegate


/**
 * Mock service Interface
 *
 */
class MockServiceImpl(private val delegate: BehaviorDelegate<ServiceInterface>) : ServiceInterface {
    override fun getSearchList(
        key: String?,
        location: String?,
        format: String
    ): Observable<SearchResults> {
        val mockResponse= Gson().fromJson(getMockSearchJSON(), SearchResults::class.java)
        return delegate.returningResponse(mockResponse).getSearchList("","")
    }

    override fun getWeatherDetails(
        key: String?,
        location: String?,
        days: Int,
        format: String
    ): Observable<WeatherDetailsResult> {
        val mockResponse= Gson().fromJson(getMockDetailsJSON(), WeatherDetailsResult::class.java)
        return delegate.returningResponse(mockResponse).getWeatherDetails("","")
    }

    private fun getMockSearchJSON(): String {
        return "{\n" +
                "    \"search_api\": {\n" +
                "        \"result\": [\n" +
                "            {\n" +
                "                \"areaName\": [\n" +
                "                    {\n" +
                "                        \"value\": \"London\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"country\": [\n" +
                "                    {\n" +
                "                        \"value\": \"United Kingdom\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"region\": [\n" +
                "                    {\n" +
                "                        \"value\": \"City of London, Greater London\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"latitude\": \"51.517\",\n" +
                "                \"longitude\": \"-0.106\",\n" +
                "                \"population\": \"7421228\",\n" +
                "                \"weatherUrl\": [\n" +
                "                    {\n" +
                "                        \"value\": \"https://www.worldweatheronline.com/v2/weather.aspx?q=51.5171,-0.1062\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"areaName\": [\n" +
                "                    {\n" +
                "                        \"value\": \"London\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"country\": [\n" +
                "                    {\n" +
                "                        \"value\": \"Canada\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"region\": [\n" +
                "                    {\n" +
                "                        \"value\": \"Ontario\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"latitude\": \"42.983\",\n" +
                "                \"longitude\": \"-81.250\",\n" +
                "                \"population\": \"346765\",\n" +
                "                \"weatherUrl\": [\n" +
                "                    {\n" +
                "                        \"value\": \"https://www.worldweatheronline.com/v2/weather.aspx?q=42.9833,-81.25\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"areaName\": [\n" +
                "                    {\n" +
                "                        \"value\": \"Londonderry\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"country\": [\n" +
                "                    {\n" +
                "                        \"value\": \"United States of America\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"region\": [\n" +
                "                    {\n" +
                "                        \"value\": \"New Hampshire\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"latitude\": \"42.865\",\n" +
                "                \"longitude\": \"-71.374\",\n" +
                "                \"population\": \"24474\",\n" +
                "                \"weatherUrl\": [\n" +
                "                    {\n" +
                "                        \"value\": \"https://www.worldweatheronline.com/v2/weather.aspx?q=42.865,-71.3744\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}"
    }

    private fun getMockDetailsJSON() : String{
        return "{\n" +
                "    \"data\": {\n" +
                "        \"request\": [\n" +
                "            {\n" +
                "                \"type\": \"City\",\n" +
                "                \"query\": \"London, Canada\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"current_condition\": [\n" +
                "            {\n" +
                "                \"observation_time\": \"03:53 AM\",\n" +
                "                \"temp_C\": \"1\",\n" +
                "                \"temp_F\": \"33\",\n" +
                "                \"weatherCode\": \"116\",\n" +
                "                \"weatherIconUrl\": [\n" +
                "                    {\n" +
                "                        \"value\": \"http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0004_black_low_cloud.png\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"weatherDesc\": [\n" +
                "                    {\n" +
                "                        \"value\": \"Partly cloudy\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"windspeedMiles\": \"3\",\n" +
                "                \"windspeedKmph\": \"5\",\n" +
                "                \"winddirDegree\": \"243\",\n" +
                "                \"winddir16Point\": \"WSW\",\n" +
                "                \"precipMM\": \"0.0\",\n" +
                "                \"precipInches\": \"0.0\",\n" +
                "                \"humidity\": \"66\",\n" +
                "                \"visibility\": \"10\",\n" +
                "                \"visibilityMiles\": \"6\",\n" +
                "                \"pressure\": \"1027\",\n" +
                "                \"pressureInches\": \"31\",\n" +
                "                \"cloudcover\": \"42\",\n" +
                "                \"FeelsLikeC\": \"-1\",\n" +
                "                \"FeelsLikeF\": \"30\",\n" +
                "                \"uvIndex\": 0\n" +
                "            }\n" +
                "        ],\n" +
                "        \"weather\": [\n" +
                "            {\n" +
                "                \"date\": \"2019-11-28\",\n" +
                "                \"astronomy\": [\n" +
                "                    {\n" +
                "                        \"sunrise\": \"07:33 AM\",\n" +
                "                        \"sunset\": \"04:52 PM\",\n" +
                "                        \"moonrise\": \"09:34 AM\",\n" +
                "                        \"moonset\": \"06:47 PM\",\n" +
                "                        \"moon_phase\": \"Waxing Crescent\",\n" +
                "                        \"moon_illumination\": \"4\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"maxtempC\": \"2\",\n" +
                "                \"maxtempF\": \"35\",\n" +
                "                \"mintempC\": \"-1\",\n" +
                "                \"mintempF\": \"30\",\n" +
                "                \"avgtempC\": \"1\",\n" +
                "                \"avgtempF\": \"34\",\n" +
                "                \"totalSnow_cm\": \"0.0\",\n" +
                "                \"sunHour\": \"5.2\",\n" +
                "                \"uvIndex\": \"1\",\n" +
                "                \"hourly\": [\n" +
                "                    {\n" +
                "                        \"time\": \"0\",\n" +
                "                        \"tempC\": \"2\",\n" +
                "                        \"tempF\": \"35\",\n" +
                "                        \"windspeedMiles\": \"19\",\n" +
                "                        \"windspeedKmph\": \"31\",\n" +
                "                        \"winddirDegree\": \"273\",\n" +
                "                        \"winddir16Point\": \"W\",\n" +
                "                        \"weatherCode\": \"296\",\n" +
                "                        \"weatherIconUrl\": [\n" +
                "                            {\n" +
                "                                \"value\": \"http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0033_cloudy_with_light_rain_night.png\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"weatherDesc\": [\n" +
                "                            {\n" +
                "                                \"value\": \"Light rain\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"precipMM\": \"0.7\",\n" +
                "                        \"precipInches\": \"0.0\",\n" +
                "                        \"humidity\": \"90\",\n" +
                "                        \"visibility\": \"9\",\n" +
                "                        \"visibilityMiles\": \"5\",\n" +
                "                        \"pressure\": \"1006\",\n" +
                "                        \"pressureInches\": \"30\",\n" +
                "                        \"cloudcover\": \"100\",\n" +
                "                        \"HeatIndexC\": \"4\",\n" +
                "                        \"HeatIndexF\": \"39\",\n" +
                "                        \"DewPointC\": \"2\",\n" +
                "                        \"DewPointF\": \"36\",\n" +
                "                        \"WindChillC\": \"0\",\n" +
                "                        \"WindChillF\": \"32\",\n" +
                "                        \"WindGustMiles\": \"27\",\n" +
                "                        \"WindGustKmph\": \"43\",\n" +
                "                        \"FeelsLikeC\": \"0\",\n" +
                "                        \"FeelsLikeF\": \"32\",\n" +
                "                        \"chanceofrain\": \"23\",\n" +
                "                        \"chanceofremdry\": \"0\",\n" +
                "                        \"chanceofwindy\": \"0\",\n" +
                "                        \"chanceofovercast\": \"93\",\n" +
                "                        \"chanceofsunshine\": \"0\",\n" +
                "                        \"chanceoffrost\": \"0\",\n" +
                "                        \"chanceofhightemp\": \"0\",\n" +
                "                        \"chanceoffog\": \"0\",\n" +
                "                        \"chanceofsnow\": \"50\",\n" +
                "                        \"chanceofthunder\": \"0\",\n" +
                "                        \"uvIndex\": \"0\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"time\": \"300\",\n" +
                "                        \"tempC\": \"4\",\n" +
                "                        \"tempF\": \"39\",\n" +
                "                        \"windspeedMiles\": \"20\",\n" +
                "                        \"windspeedKmph\": \"32\",\n" +
                "                        \"winddirDegree\": \"305\",\n" +
                "                        \"winddir16Point\": \"NW\",\n" +
                "                        \"weatherCode\": \"227\",\n" +
                "                        \"weatherIconUrl\": [\n" +
                "                            {\n" +
                "                                \"value\": \"http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0035_cloudy_with_light_snow_night.png\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"weatherDesc\": [\n" +
                "                            {\n" +
                "                                \"value\": \"Blowing snow\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"precipMM\": \"0.5\",\n" +
                "                        \"precipInches\": \"0.0\",\n" +
                "                        \"humidity\": \"87\",\n" +
                "                        \"visibility\": \"7\",\n" +
                "                        \"visibilityMiles\": \"4\",\n" +
                "                        \"pressure\": \"1011\",\n" +
                "                        \"pressureInches\": \"30\",\n" +
                "                        \"cloudcover\": \"100\",\n" +
                "                        \"HeatIndexC\": \"4\",\n" +
                "                        \"HeatIndexF\": \"39\",\n" +
                "                        \"DewPointC\": \"2\",\n" +
                "                        \"DewPointF\": \"35\",\n" +
                "                        \"WindChillC\": \"-2\",\n" +
                "                        \"WindChillF\": \"29\",\n" +
                "                        \"WindGustMiles\": \"27\",\n" +
                "                        \"WindGustKmph\": \"44\",\n" +
                "                        \"FeelsLikeC\": \"-2\",\n" +
                "                        \"FeelsLikeF\": \"29\",\n" +
                "                        \"chanceofrain\": \"46\",\n" +
                "                        \"chanceofremdry\": \"30\",\n" +
                "                        \"chanceofwindy\": \"0\",\n" +
                "                        \"chanceofovercast\": \"90\",\n" +
                "                        \"chanceofsunshine\": \"6\",\n" +
                "                        \"chanceoffrost\": \"3\",\n" +
                "                        \"chanceofhightemp\": \"0\",\n" +
                "                        \"chanceoffog\": \"0\",\n" +
                "                        \"chanceofsnow\": \"0\",\n" +
                "                        \"chanceofthunder\": \"0\",\n" +
                "                        \"uvIndex\": \"0\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"time\": \"600\",\n" +
                "                        \"tempC\": \"1\",\n" +
                "                        \"tempF\": \"35\",\n" +
                "                        \"windspeedMiles\": \"22\",\n" +
                "                        \"windspeedKmph\": \"35\",\n" +
                "                        \"winddirDegree\": \"315\",\n" +
                "                        \"winddir16Point\": \"NW\",\n" +
                "                        \"weatherCode\": \"230\",\n" +
                "                        \"weatherIconUrl\": [\n" +
                "                            {\n" +
                "                                \"value\": \"http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0036_cloudy_with_heavy_snow_night.png\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"weatherDesc\": [\n" +
                "                            {\n" +
                "                                \"value\": \"Blizzard\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"precipMM\": \"0.0\",\n" +
                "                        \"precipInches\": \"0.0\",\n" +
                "                        \"humidity\": \"83\",\n" +
                "                        \"visibility\": \"1\",\n" +
                "                        \"visibilityMiles\": \"0\",\n" +
                "                        \"pressure\": \"1017\",\n" +
                "                        \"pressureInches\": \"31\",\n" +
                "                        \"cloudcover\": \"97\",\n" +
                "                        \"HeatIndexC\": \"1\",\n" +
                "                        \"HeatIndexF\": \"35\",\n" +
                "                        \"DewPointC\": \"-1\",\n" +
                "                        \"DewPointF\": \"30\",\n" +
                "                        \"WindChillC\": \"-5\",\n" +
                "                        \"WindChillF\": \"23\",\n" +
                "                        \"WindGustMiles\": \"29\",\n" +
                "                        \"WindGustKmph\": \"46\",\n" +
                "                        \"FeelsLikeC\": \"-5\",\n" +
                "                        \"FeelsLikeF\": \"23\",\n" +
                "                        \"chanceofrain\": \"0\",\n" +
                "                        \"chanceofremdry\": \"61\",\n" +
                "                        \"chanceofwindy\": \"0\",\n" +
                "                        \"chanceofovercast\": \"89\",\n" +
                "                        \"chanceofsunshine\": \"13\",\n" +
                "                        \"chanceoffrost\": \"12\",\n" +
                "                        \"chanceofhightemp\": \"0\",\n" +
                "                        \"chanceoffog\": \"0\",\n" +
                "                        \"chanceofsnow\": \"29\",\n" +
                "                        \"chanceofthunder\": \"0\",\n" +
                "                        \"uvIndex\": \"0\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"time\": \"900\",\n" +
                "                        \"tempC\": \"1\",\n" +
                "                        \"tempF\": \"33\",\n" +
                "                        \"windspeedMiles\": \"19\",\n" +
                "                        \"windspeedKmph\": \"31\",\n" +
                "                        \"winddirDegree\": \"330\",\n" +
                "                        \"winddir16Point\": \"NNW\",\n" +
                "                        \"weatherCode\": \"122\",\n" +
                "                        \"weatherIconUrl\": [\n" +
                "                            {\n" +
                "                                \"value\": \"http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0004_black_low_cloud.png\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"weatherDesc\": [\n" +
                "                            {\n" +
                "                                \"value\": \"Overcast\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"precipMM\": \"0.1\",\n" +
                "                        \"precipInches\": \"0.0\",\n" +
                "                        \"humidity\": \"83\",\n" +
                "                        \"visibility\": \"3\",\n" +
                "                        \"visibilityMiles\": \"2\",\n" +
                "                        \"pressure\": \"1023\",\n" +
                "                        \"pressureInches\": \"31\",\n" +
                "                        \"cloudcover\": \"95\",\n" +
                "                        \"HeatIndexC\": \"1\",\n" +
                "                        \"HeatIndexF\": \"33\",\n" +
                "                        \"DewPointC\": \"-2\",\n" +
                "                        \"DewPointF\": \"28\",\n" +
                "                        \"WindChillC\": \"-6\",\n" +
                "                        \"WindChillF\": \"21\",\n" +
                "                        \"WindGustMiles\": \"25\",\n" +
                "                        \"WindGustKmph\": \"40\",\n" +
                "                        \"FeelsLikeC\": \"-6\",\n" +
                "                        \"FeelsLikeF\": \"21\",\n" +
                "                        \"chanceofrain\": \"0\",\n" +
                "                        \"chanceofremdry\": \"31\",\n" +
                "                        \"chanceofwindy\": \"0\",\n" +
                "                        \"chanceofovercast\": \"89\",\n" +
                "                        \"chanceofsunshine\": \"5\",\n" +
                "                        \"chanceoffrost\": \"15\",\n" +
                "                        \"chanceofhightemp\": \"0\",\n" +
                "                        \"chanceoffog\": \"0\",\n" +
                "                        \"chanceofsnow\": \"59\",\n" +
                "                        \"chanceofthunder\": \"0\",\n" +
                "                        \"uvIndex\": \"1\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"time\": \"1200\",\n" +
                "                        \"tempC\": \"1\",\n" +
                "                        \"tempF\": \"34\",\n" +
                "                        \"windspeedMiles\": \"15\",\n" +
                "                        \"windspeedKmph\": \"25\",\n" +
                "                        \"winddirDegree\": \"339\",\n" +
                "                        \"winddir16Point\": \"NNW\",\n" +
                "                        \"weatherCode\": \"119\",\n" +
                "                        \"weatherIconUrl\": [\n" +
                "                            {\n" +
                "                                \"value\": \"http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0003_white_cloud.png\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"weatherDesc\": [\n" +
                "                            {\n" +
                "                                \"value\": \"Cloudy\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"precipMM\": \"0.0\",\n" +
                "                        \"precipInches\": \"0.0\",\n" +
                "                        \"humidity\": \"77\",\n" +
                "                        \"visibility\": \"10\",\n" +
                "                        \"visibilityMiles\": \"6\",\n" +
                "                        \"pressure\": \"1026\",\n" +
                "                        \"pressureInches\": \"31\",\n" +
                "                        \"cloudcover\": \"89\",\n" +
                "                        \"HeatIndexC\": \"1\",\n" +
                "                        \"HeatIndexF\": \"34\",\n" +
                "                        \"DewPointC\": \"-3\",\n" +
                "                        \"DewPointF\": \"27\",\n" +
                "                        \"WindChillC\": \"-5\",\n" +
                "                        \"WindChillF\": \"24\",\n" +
                "                        \"WindGustMiles\": \"18\",\n" +
                "                        \"WindGustKmph\": \"30\",\n" +
                "                        \"FeelsLikeC\": \"-5\",\n" +
                "                        \"FeelsLikeF\": \"24\",\n" +
                "                        \"chanceofrain\": \"0\",\n" +
                "                        \"chanceofremdry\": \"90\",\n" +
                "                        \"chanceofwindy\": \"0\",\n" +
                "                        \"chanceofovercast\": \"85\",\n" +
                "                        \"chanceofsunshine\": \"16\",\n" +
                "                        \"chanceoffrost\": \"13\",\n" +
                "                        \"chanceofhightemp\": \"0\",\n" +
                "                        \"chanceoffog\": \"0\",\n" +
                "                        \"chanceofsnow\": \"0\",\n" +
                "                        \"chanceofthunder\": \"0\",\n" +
                "                        \"uvIndex\": \"1\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"time\": \"1500\",\n" +
                "                        \"tempC\": \"2\",\n" +
                "                        \"tempF\": \"35\",\n" +
                "                        \"windspeedMiles\": \"13\",\n" +
                "                        \"windspeedKmph\": \"21\",\n" +
                "                        \"winddirDegree\": \"340\",\n" +
                "                        \"winddir16Point\": \"NNW\",\n" +
                "                        \"weatherCode\": \"116\",\n" +
                "                        \"weatherIconUrl\": [\n" +
                "                            {\n" +
                "                                \"value\": \"http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0002_sunny_intervals.png\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"weatherDesc\": [\n" +
                "                            {\n" +
                "                                \"value\": \"Partly cloudy\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"precipMM\": \"0.0\",\n" +
                "                        \"precipInches\": \"0.0\",\n" +
                "                        \"humidity\": \"74\",\n" +
                "                        \"visibility\": \"10\",\n" +
                "                        \"visibilityMiles\": \"6\",\n" +
                "                        \"pressure\": \"1027\",\n" +
                "                        \"pressureInches\": \"31\",\n" +
                "                        \"cloudcover\": \"47\",\n" +
                "                        \"HeatIndexC\": \"2\",\n" +
                "                        \"HeatIndexF\": \"35\",\n" +
                "                        \"DewPointC\": \"-3\",\n" +
                "                        \"DewPointF\": \"27\",\n" +
                "                        \"WindChillC\": \"-3\",\n" +
                "                        \"WindChillF\": \"26\",\n" +
                "                        \"WindGustMiles\": \"16\",\n" +
                "                        \"WindGustKmph\": \"25\",\n" +
                "                        \"FeelsLikeC\": \"-3\",\n" +
                "                        \"FeelsLikeF\": \"26\",\n" +
                "                        \"chanceofrain\": \"0\",\n" +
                "                        \"chanceofremdry\": \"81\",\n" +
                "                        \"chanceofwindy\": \"0\",\n" +
                "                        \"chanceofovercast\": \"68\",\n" +
                "                        \"chanceofsunshine\": \"40\",\n" +
                "                        \"chanceoffrost\": \"10\",\n" +
                "                        \"chanceofhightemp\": \"0\",\n" +
                "                        \"chanceoffog\": \"0\",\n" +
                "                        \"chanceofsnow\": \"0\",\n" +
                "                        \"chanceofthunder\": \"0\",\n" +
                "                        \"uvIndex\": \"2\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"time\": \"1800\",\n" +
                "                        \"tempC\": \"0\",\n" +
                "                        \"tempF\": \"33\",\n" +
                "                        \"windspeedMiles\": \"11\",\n" +
                "                        \"windspeedKmph\": \"18\",\n" +
                "                        \"winddirDegree\": \"344\",\n" +
                "                        \"winddir16Point\": \"NNW\",\n" +
                "                        \"weatherCode\": \"116\",\n" +
                "                        \"weatherIconUrl\": [\n" +
                "                            {\n" +
                "                                \"value\": \"http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0004_black_low_cloud.png\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"weatherDesc\": [\n" +
                "                            {\n" +
                "                                \"value\": \"Partly cloudy\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"precipMM\": \"0.0\",\n" +
                "                        \"precipInches\": \"0.0\",\n" +
                "                        \"humidity\": \"74\",\n" +
                "                        \"visibility\": \"10\",\n" +
                "                        \"visibilityMiles\": \"6\",\n" +
                "                        \"pressure\": \"1029\",\n" +
                "                        \"pressureInches\": \"31\",\n" +
                "                        \"cloudcover\": \"7\",\n" +
                "                        \"HeatIndexC\": \"0\",\n" +
                "                        \"HeatIndexF\": \"33\",\n" +
                "                        \"DewPointC\": \"-4\",\n" +
                "                        \"DewPointF\": \"25\",\n" +
                "                        \"WindChillC\": \"-5\",\n" +
                "                        \"WindChillF\": \"24\",\n" +
                "                        \"WindGustMiles\": \"16\",\n" +
                "                        \"WindGustKmph\": \"26\",\n" +
                "                        \"FeelsLikeC\": \"-5\",\n" +
                "                        \"FeelsLikeF\": \"24\",\n" +
                "                        \"chanceofrain\": \"0\",\n" +
                "                        \"chanceofremdry\": \"83\",\n" +
                "                        \"chanceofwindy\": \"0\",\n" +
                "                        \"chanceofovercast\": \"40\",\n" +
                "                        \"chanceofsunshine\": \"83\",\n" +
                "                        \"chanceoffrost\": \"20\",\n" +
                "                        \"chanceofhightemp\": \"0\",\n" +
                "                        \"chanceoffog\": \"0\",\n" +
                "                        \"chanceofsnow\": \"0\",\n" +
                "                        \"chanceofthunder\": \"0\",\n" +
                "                        \"uvIndex\": \"0\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"time\": \"2100\",\n" +
                "                        \"tempC\": \"-1\",\n" +
                "                        \"tempF\": \"30\",\n" +
                "                        \"windspeedMiles\": \"9\",\n" +
                "                        \"windspeedKmph\": \"14\",\n" +
                "                        \"winddirDegree\": \"238\",\n" +
                "                        \"winddir16Point\": \"WSW\",\n" +
                "                        \"weatherCode\": \"116\",\n" +
                "                        \"weatherIconUrl\": [\n" +
                "                            {\n" +
                "                                \"value\": \"http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0004_black_low_cloud.png\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"weatherDesc\": [\n" +
                "                            {\n" +
                "                                \"value\": \"Partly cloudy\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"precipMM\": \"0.0\",\n" +
                "                        \"precipInches\": \"0.0\",\n" +
                "                        \"humidity\": \"78\",\n" +
                "                        \"visibility\": \"10\",\n" +
                "                        \"visibilityMiles\": \"6\",\n" +
                "                        \"pressure\": \"1031\",\n" +
                "                        \"pressureInches\": \"31\",\n" +
                "                        \"cloudcover\": \"11\",\n" +
                "                        \"HeatIndexC\": \"-1\",\n" +
                "                        \"HeatIndexF\": \"30\",\n" +
                "                        \"DewPointC\": \"-4\",\n" +
                "                        \"DewPointF\": \"24\",\n" +
                "                        \"WindChillC\": \"-6\",\n" +
                "                        \"WindChillF\": \"22\",\n" +
                "                        \"WindGustMiles\": \"13\",\n" +
                "                        \"WindGustKmph\": \"22\",\n" +
                "                        \"FeelsLikeC\": \"-6\",\n" +
                "                        \"FeelsLikeF\": \"22\",\n" +
                "                        \"chanceofrain\": \"0\",\n" +
                "                        \"chanceofremdry\": \"88\",\n" +
                "                        \"chanceofwindy\": \"0\",\n" +
                "                        \"chanceofovercast\": \"38\",\n" +
                "                        \"chanceofsunshine\": \"85\",\n" +
                "                        \"chanceoffrost\": \"40\",\n" +
                "                        \"chanceofhightemp\": \"0\",\n" +
                "                        \"chanceoffog\": \"0\",\n" +
                "                        \"chanceofsnow\": \"0\",\n" +
                "                        \"chanceofthunder\": \"0\",\n" +
                "                        \"uvIndex\": \"0\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"ClimateAverages\": [\n" +
                "            {\n" +
                "                \"month\": [\n" +
                "                    {\n" +
                "                        \"index\": \"1\",\n" +
                "                        \"name\": \"January\",\n" +
                "                        \"avgMinTemp\": \"-8.5\",\n" +
                "                        \"avgMinTemp_F\": \"16.7\",\n" +
                "                        \"absMaxTemp\": \"0.04506448\",\n" +
                "                        \"absMaxTemp_F\": \"32.1\",\n" +
                "                        \"avgDailyRainfall\": \"0.65\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"index\": \"2\",\n" +
                "                        \"name\": \"February\",\n" +
                "                        \"avgMinTemp\": \"-8.1\",\n" +
                "                        \"avgMinTemp_F\": \"17.5\",\n" +
                "                        \"absMaxTemp\": \"2.933357\",\n" +
                "                        \"absMaxTemp_F\": \"37.3\",\n" +
                "                        \"avgDailyRainfall\": \"0.66\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"index\": \"3\",\n" +
                "                        \"name\": \"March\",\n" +
                "                        \"avgMinTemp\": \"-3.5\",\n" +
                "                        \"avgMinTemp_F\": \"25.7\",\n" +
                "                        \"absMaxTemp\": \"10.84642\",\n" +
                "                        \"absMaxTemp_F\": \"51.5\",\n" +
                "                        \"avgDailyRainfall\": \"0.54\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"index\": \"4\",\n" +
                "                        \"name\": \"April\",\n" +
                "                        \"avgMinTemp\": \"2.4\",\n" +
                "                        \"avgMinTemp_F\": \"36.3\",\n" +
                "                        \"absMaxTemp\": \"14.46483\",\n" +
                "                        \"absMaxTemp_F\": \"58.0\",\n" +
                "                        \"avgDailyRainfall\": \"0.93\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"index\": \"5\",\n" +
                "                        \"name\": \"May\",\n" +
                "                        \"avgMinTemp\": \"8.9\",\n" +
                "                        \"avgMinTemp_F\": \"48.1\",\n" +
                "                        \"absMaxTemp\": \"21.98406\",\n" +
                "                        \"absMaxTemp_F\": \"71.6\",\n" +
                "                        \"avgDailyRainfall\": \"0.89\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"index\": \"6\",\n" +
                "                        \"name\": \"June\",\n" +
                "                        \"avgMinTemp\": \"12.7\",\n" +
                "                        \"avgMinTemp_F\": \"54.8\",\n" +
                "                        \"absMaxTemp\": \"23.89206\",\n" +
                "                        \"absMaxTemp_F\": \"75.0\",\n" +
                "                        \"avgDailyRainfall\": \"0.86\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"index\": \"7\",\n" +
                "                        \"name\": \"July\",\n" +
                "                        \"avgMinTemp\": \"15.3\",\n" +
                "                        \"avgMinTemp_F\": \"59.5\",\n" +
                "                        \"absMaxTemp\": \"26.76313\",\n" +
                "                        \"absMaxTemp_F\": \"80.2\",\n" +
                "                        \"avgDailyRainfall\": \"1.28\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"index\": \"8\",\n" +
                "                        \"name\": \"August\",\n" +
                "                        \"avgMinTemp\": \"14.9\",\n" +
                "                        \"avgMinTemp_F\": \"58.8\",\n" +
                "                        \"absMaxTemp\": \"27.69116\",\n" +
                "                        \"absMaxTemp_F\": \"81.8\",\n" +
                "                        \"avgDailyRainfall\": \"0.76\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"index\": \"9\",\n" +
                "                        \"name\": \"September\",\n" +
                "                        \"avgMinTemp\": \"11.8\",\n" +
                "                        \"avgMinTemp_F\": \"53.3\",\n" +
                "                        \"absMaxTemp\": \"24.30623\",\n" +
                "                        \"absMaxTemp_F\": \"75.8\",\n" +
                "                        \"avgDailyRainfall\": \"0.76\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"index\": \"10\",\n" +
                "                        \"name\": \"October\",\n" +
                "                        \"avgMinTemp\": \"6.7\",\n" +
                "                        \"avgMinTemp_F\": \"44.0\",\n" +
                "                        \"absMaxTemp\": \"16.77835\",\n" +
                "                        \"absMaxTemp_F\": \"62.2\",\n" +
                "                        \"avgDailyRainfall\": \"1.21\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"index\": \"11\",\n" +
                "                        \"name\": \"November\",\n" +
                "                        \"avgMinTemp\": \"1.1\",\n" +
                "                        \"avgMinTemp_F\": \"34.0\",\n" +
                "                        \"absMaxTemp\": \"10.2653\",\n" +
                "                        \"absMaxTemp_F\": \"50.5\",\n" +
                "                        \"avgDailyRainfall\": \"0.70\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"index\": \"12\",\n" +
                "                        \"name\": \"December\",\n" +
                "                        \"avgMinTemp\": \"-3.7\",\n" +
                "                        \"avgMinTemp_F\": \"25.3\",\n" +
                "                        \"absMaxTemp\": \"6.634599\",\n" +
                "                        \"absMaxTemp_F\": \"43.9\",\n" +
                "                        \"avgDailyRainfall\": \"0.63\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}"
    }

}
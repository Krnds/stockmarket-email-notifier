# Stock market email notifier 📩🏦

Tired of checking up your index funds ? 📈
This is a straighforward app to send automatic emails about index funds price variations.
It will send a mail about today's index fund price (max and low) and the minimum price within the last 30 days.

🧑🏼‍🍳 Using Java 17, SpringBoot, JavaMail and OKHTTP client.

## 1. Before using 🛠️

First, set your own API credentials (host and password). I personally chose [Yahoo finance API](https://rapidapi.com/asepscareer/api/yahoo-finance97/) from RapidAPI. Once you've suscribed, put all your personal infos in a properties file, like this (the host is set up for gmail)

```yaml
spring:
  mail:
    default-encoding: UTF-8
    # change host if needed
    host: smtp.gmail.com
    username: <email>
    password: <generated-password>
    port: 587
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
    protocol: smtp
    test-connection: false
api:
  key: <api-key>
  host: <api-url>

```

Since may 2022, Gmail require to use an app specific password to connect to your account. You need to turn on 2SV. The official documentation is found [here](https://support.google.com/accounts/answer/185833?hl=en)

In the main class, change the ISIN code symbol you want to track.

```java
String stockMarketSymbol = "MSFT";
```

For example if you want to track the `Microsoft Corporation Common Stock`, the symbol is `MSFT`

## 3. API data 🗃️

The Yahoo finance API returns JSON data like this :

```json
{
  "data": [
    {
      "Close": 247.1100006104,
      "Date": 1668142800000,
      "Dividends": 0,
      "High": 247.9900054932,
      "Low": 241.9299926758,
      "Open": 242.9900054932,
      "Stock Splits": 0,
      "Volume": 34600900
    }
  ],
  "message": "Success",
  "status": 200
}
```

This app only uses the high, low and date values.

## 4. Future improvements ✨

- [ ] make currency dynamic
- [ ] set up a cron to send automatic emails
- [ ] make a dockerfile to facilitate use
- [ ] set up an email template (Bootsrap email for example)
- [ ] make a UI
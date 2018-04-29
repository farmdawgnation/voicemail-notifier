# voicemail-notifier

A simple application to text me when Twilio records a voicemail on my behalf.
I use this application on a phone number that had to be made public as a way for
spammers and auto-dialers to not interrupt my workday, but to still be able to
receive any important or legal messages that might be left for me.

## Using

### Deploying the application

First, you'll need a [Twilio](https://www.twilio.com) account. Once you get it,
you'll need to purchase a phone number that can receive calls and send you
SMS messages. Then you can proceed with setup.
([direct link to phone number management](https://www.twilio.com/console/phone-numbers/incoming))

This is distributed as a Docker image if you'd like to use it for yourself.
You can find it at: `farmdawgnation/voicemail-notifier` on Docker Hub.

You will need to provide the following environment variables for it to work:

* `ACCOUNT_SID` - Account SID for your account
* `AUTH_TOKEN` or `AUTH_TOKEN_FILE` - Auth token for your account. If the file
  variant is defined the auth token will be read from a file, which is useful
  if you're mounting a secret into the file system.
* `SOURCE_NUMBER` - A phone number to send from. This will be the number you
  purchased on your Twilio account. This should be formatted as "+11234567890"
  for US numbers.
* `TARGET_NUMBER` - The phone number to send the notification to. This will be
  a real phone number that you use. This should be formatted as "+11234567890"
  for US numbers.
* `VALIDATION_ENABLED` - Set this to "true" to enforce Twilio request validation.
  Any incoming requests from Twilio are signed and turning this on will enforce
  checking those signatures. I highly recommend that you turn this setting on.
* `BASE_URL` - The Base URL that Twilio will use to contact your server
  including the http / https prefix. (e.g. http://domain.to.your.notifier)

Once you've got your docker container deployed, you should be able to send
requests to it, but you'll likely get HTTP 401 responses unless you disabled
validation. Once it's up and running you can configure Twilio's end.

### Configuring Twilio

Once your app is up and running, you need to tell Twilio what to do with your
number. You'll do this in the "Runtime" section of your Twilio account under
"TwiML Bins" ([direct link](https://www.twilio.com/console/runtime/twiml-bins)).
You will specifically need to define:

* A TwiML bin to prompt the caller to record a message
* A TwiML bin to end the call once the caller finishes

Here are the examples I use:

**Hangup (used after voicemail is recorded):**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Response>
  <Hangup></Hangup>
</Response>
```

**Record a 30 second call**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Response>
  <Say voice="alice">Record your message at the beep</Say>
  <Record action="URL_TO_YOUR_HANGUP_BIN"
          recordingStatusCallback="URL_TO_YOUR_NOTIFY_DEPLOYMENT"
          timeout="10"
          transcribe="false"
          maxLength="30" />
  <Hangup />
</Response>
```

In the record call bin, replace `URL_TO_YOUR_HANGUP_BIN` with the URL Twilio
generates for your Hangup bin (starts with `https://handler.twilio.com/twiml/`),
and then replace `URL_TO_YOUR_NOTIFY_DEPLOYMENT` to the URL Twilio should hit
to your notifier service. This should be `http://domain.to.your.notifier/notify`
or something equivalent and it **must** match your `BASE_URL` or Twilio request
validation will fail.

After you've created these bins, return to your
[phone management screen](https://www.twilio.com/console/phone-numbers/incoming)
and click on the phone number you purchased for this voicemail service.

Under the voice and fax section ensure the following settings are selected:

* Accept Incoming: Voice Calls
* Configure with: Webhooks, TwiML Bins, Functions, Studio, or Proxy
* A Call Comes In: (name of your record message bin)

Save your changes.

That should be it. If things are working you should now be able to call your
phone number, record a message, and get an SMS that you have a voicemail to
listen to with a direct link to it.

You can also list and listen to the recordings in your Twilio Console.
([direct link](https://www.twilio.com/console/voice/recordings/recording-logs)).

## Development

### Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

### Running

To start a web server for the application, run:

    lein ring server-headless

## License

Copyright © 2018 Matt Farmer. Licensed under the terms of the Apache 2.0
license.

# voicemail-notifier

A simple application to text me when Twilio records a voicemail on my behalf.
This is distributed as a Docker image if you'd like to use it for yourself.
You can find it at: farmdawgnation/voicemail-notifier on Docker Hub.

You will need to provide the following environment variables:

* `ACCOUNT_SID` - Account SID for your account
* `AUTH_TOKEN` - Auth token for your account
* `SOURCE_NUMBER` - A phone number to send from
* `TARGET_NUMBER` - The phone number to send to
* `CALLBACK_SECRET` - An arbitrary secret of your choosing to avoid someone
  discovering this and just spamming the hell out of you by hitting the URL
  over and over. :)

Deploy this then configure `http://domain.to.your.notifer/notify/...` as the
`recordingStatusCallback` on your `<Record>` TwiML element.

For example if my secret were `abcd` might set my `recordingStatusCallback` as
`http://notifier.farm.dog/notify/abcd`.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server-headless

## License

Copyright © 2018 Matt Farmer

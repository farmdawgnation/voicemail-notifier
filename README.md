# voicemail-notifier

A simple application to text me when Twilio records a voicemail on my behalf.
This is distributed as a Docker image if you'd like to use it for yourself.
You can find it at: farmdawgnation/voicemail-notifier on Docker Hub.

You will need to provide the following environment variables:

* `ACCOUNT_SID` - Account SID for your account
* `AUTH_TOKEN` or `AUTH_TOKEN_FILE` - Auth token for your account. If the file
  variant is defined the auth token will be read from a file, which is useful
  if you're mounting a secret into the file system.
* `SOURCE_NUMBER` - A phone number to send from
* `TARGET_NUMBER` - The phone number to send the notification to
* `VALIDATION_ENABLED` - Set this to true to enforce Twilio request validation.
  Any incoming requests from Twilio are signed and turning this on will enforce
  checking those signatures.

Deploy this then configure `http://domain.to.your.notifer/notify` as the
`recordingStatusCallback` on your `<Record>` TwiML element.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server-headless

## License

Copyright © 2018 Matt Farmer

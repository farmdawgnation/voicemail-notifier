#!/bin/bash

set -e

echo "Starting"
lein ring uberjar
docker build -t farmdawgnation/voicemail-notifier .
docker push farmdawgnation/voicemail-notifier
echo "Done."

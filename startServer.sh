#!/bin/bash
java -jar ./target/team01-1.0-SNAPSHOT.jar &      # You send it in background
MyPID=$!                        # You sign it's PID
echo $MyPID                     # You print to terminal
echo "kill $MyPID" > MyStop.sh  # Write the the command kill pid in MyStop.sh
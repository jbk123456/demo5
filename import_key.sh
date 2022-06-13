#!/bin/bash

keytool -rfc -export -keystore ./frontendUI/target/liberty/wlp/usr/servers/defaultServer/resources/security/key.p12  -alias default -file publickey.cer -storepass EE18tsIXIY3ieIGe09tD6Oj

keytool -importcert  -file publickey.cer  -alias ssl    -keystore ./backendServices/target/liberty/wlp/usr/servers/defaultServer/resources/security/key.p12  -storepass 8bSaVyZLiVumSm3YNThO2rB

cp ./frontendUI/target/liberty/wlp/usr/servers/defaultServer/resources/security/key.p12 ./frontendUI/src/main/liberty/config/resources/security/key.p12
cp ./backendServices/target/liberty/wlp/usr/servers/defaultServer/resources/security/key.p12 ./backendServices/src/main/liberty/config/resources/security/key.p12


keytool -v -list -keystore ./frontendUI/src/main/liberty/config/resources/security/key.p12 -storepass EE18tsIXIY3ieIGe09tD6Oj
keytool -v -list -keystore ./backendServices/src/main/liberty/config/resources/security/key.p12 -storepass 8bSaVyZLiVumSm3YNThO2rB

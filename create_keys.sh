#!/bin/bash


rm -f *.jks *.cer


keytool -genkey -alias app -keyalg RSA -keypass secret -storepass secret -keystore private.p12
keytool -export -alias app -storepass secret -file server.cer -keystore private.p12
keytool -import -v -trustcacerts -alias app -file server.cer -keystore public.p12 -keypass secret -storepass secret

keytool -importkeystore -srckeystore private.p12 -srcstoretype pkcs12 -srcalias app -destkeystore private.jks -deststoretype jks -deststorepass secret -destalias app
keytool -importkeystore -srckeystore public.p12 -srcstoretype pkcs12 -srcalias app -destkeystore public.jks -deststoretype jks -deststorepass secret -destalias app

cp public.jks ./frontendUI/src/main/liberty/config/resources/security/public.jks
cp private.jks ./backendServices/src/main/liberty/config/resources/security/private.jks

cp public.p12 ./frontendUI/src/main/liberty/config/resources/security/public.p12
cp private.p12 ./backendServices/src/main/liberty/config/resources/security/private.p12


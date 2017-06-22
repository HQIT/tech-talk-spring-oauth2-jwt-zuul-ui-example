# Spring boot OAuth2 Example

a simple example, use *Spring boot*, *Spting cloud oauth2*, *Spring security*, *Netflex zuul*, demo how to config **OAuth2 Server**, **UI Server** and **Resource Server**. use JWT as token, not user-info uri to get the authorized user's info.

## OAuth2 Server

oauth2 authentication provider

## Resource Server

provide an api ``/test`` saying ``HELLO, WORKS``

## UI-Server(*Also Zuul Gateway*)

* proxy access to ui-server and resource-server
``
/api/** -> http(s)://{resource-server-hostname}
/ -> http(s)://{ui-server-hostname}
``

## IMPORTANT

if ui-server and oauth2-server launched on the same host (even not on the same port), should set one of servers' context-path to anything but ``/``, cuz ``Set-Cookie`` will override each other, make login fail (error log say some CSRF exception, not that). actually JSESSIONID change make the OAuthClientContext re-generated is the real reason

make a try!

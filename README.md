# nocrash

This is a stable version and does not use third party library

This library will never let your app crashed instead it will throw a custom activity to describe something went wrong.

TODO: crash report.. automatic crash report to mail

Usage:

1. Add this to your build.gradle

repositories { // ... maven { url "https://jitpack.io" } }

dependencies { compile 'com.github.praslnx8:nocrash:beta' }

2. In your appState(or the class that you extend application) add this

super.onCreate();
NoCrashHandler.install(getApplicationContext());

//if you want custom activity on crash instead of default library activity to shown on crash

NoCrashHandler.setCustomCrashActivity(CustomCrashActivity.class);

    In your customActivity if you want to restart the app

    NoCrashHandler.restartApplicationWithIntent(CustomCrashActivity.this, getIntent()); //to restart the app

    NoCrashHandler.closeApplication(CustomCrashActivity.this); //to close the app

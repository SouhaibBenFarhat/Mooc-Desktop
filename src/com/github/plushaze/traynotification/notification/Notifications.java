package com.github.plushaze.traynotification.notification;

public enum Notifications implements Notification {

    INFORMATION("com/esprit/mooc/ressources/info.png", "#2C54AB"),
    NOTICE("com/esprit/mooc/ressources/notice.png", "#8D9695"),
    SUCCESS("com/esprit/mooc/ressources/success.png", "#009961"),
    WARNING("com/esprit/mooc/ressources/warning.png", "#E23E0A"),
    ERROR("com/esprit/mooc/ressources/error.png", "#CC0033");

    private final String urlResource;
    private final String paintHex;

    Notifications(String urlResource, String paintHex) {
        this.urlResource = urlResource;
        this.paintHex = paintHex;
    }

    @Override
    public String getURLResource() {
        return urlResource;
    }

    @Override
    public String getPaintHex() {
        return paintHex;
    }

}

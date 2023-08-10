package com.anadolusigorta.campaignmanagement.infrastructure.common.constant;

public final class GiftSendDescriptionMessages {

    private static final String GENERAL_ERROR = "Hediye Çeki Gönderiminde Hata Alındı.";

    public static final String POLICY_CANCELLED = "Poliçe İptal Sebebiyle İptal Edildi.";

    public static final String NO_GIFT_CODE_LEFT = "Kod Kümesinde Kullanılabilecek Kod Kalmamıştır.";

    public static final String CONTACT_EMAIL_EMPTY = "Müşteri Email Bilgisi Boş Dönmüştür.";

    public static final String CONTACT_PHONE_EMPTY = "Müşteri Telefon Numarası Bilgisi Boş Dönmüştür.";

    public static final String MISSING_POLICY_NUMBER = "Poliçe Bilgisi Bulunmamaktadır.";

    private GiftSendDescriptionMessages() {
        throw new IllegalArgumentException(GENERAL_ERROR);
    }

}

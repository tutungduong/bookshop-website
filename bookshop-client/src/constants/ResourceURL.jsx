import ApplicationConstants from "./ApplicationConstants";

const apiPath = ApplicationConstants.API_PATH;
const clientApiPath = ApplicationConstants.CLIENT_API_PATH;

class ResourceURL {
    // ADMIN

    static USER = apiPath + '/users';
    
    static PRODUCT = apiPath + '/products';
    static CATEGORY = apiPath + '/categories';
    static VARIANT = apiPath + '/variants';

    static ORDER = apiPath + '/orders';
    static ORDER_CANCELLATION_REASON = apiPath + '/order-cancellation-reasons';

    // CLIENT
    static CLIENT_CATEGORY = clientApiPath + '/categories';
    static CLIENT_PRODUCT = clientApiPath + '/products';
    static CLIENT_USER_INFO = clientApiPath + '/users/info';
    static CLIENT_USER_PERSONAL_SETTING = clientApiPath + '/users/personal';
    static CLIENT_USER_PHONE_SETTING = clientApiPath + '/users/phone';
    static CLIENT_USER_EMAIL_SETTING = clientApiPath + '/users/email';
    static CLIENT_USER_PASSWORD_SETTING = clientApiPath + '/users/password';
    static CLIENT_WISH = clientApiPath + '/wishes';
    static CLIENT_REVIEW_PRODUCT = ResourceURL.CLIENT_REVIEW + '/products'
    static CLIENT_CART = clientApiPath + '/carts';
    static CLIENT_PAYMENT_METHOD = clientApiPath + '/payment-methods';
    static CLIENT_ORDER = clientApiPath + '/orders';

    // AUTHENTICATION
    static LOGIN = apiPath + '/auth/login';
    static ADMIN_USER_INFO = apiPath + '/auth/info';
    static CLIENT_REGISTRATION = apiPath + '/auth/registration';
    static CLIENT_REGISTRATION_RESEND_TOKEN = (userId) => apiPath + `/auth/registration/${userId}/resend-token`;
    static CLIENT_REGISTRATION_CONFIRM = apiPath + '/auth/registration/confirm';
    static CLIENT_REGISTRATION_CHANGE_EMAIL = (userId) => apiPath + `/auth/registration/${userId}/change-email`;
    static CLIENT_FORGOT_PASSWORD = apiPath + '/auth/forgot-password';
    static CLIENT_RESET_PASSWORD = apiPath + '/auth/reset-password';

}
export default ResourceURL;
package mobile.fpts.com.ezmibile.view.user.login;

import android.text.InputFilter;

public class LoginPresenter {
    ILoginView view;
    public LoginPresenter(ILoginView view) {
        this.view = view;

        validateEdittext();
    }

    public void validateEdittext() {
        InputFilter filter = (source, start, end, dest, dstart, dend) -> {

            for (int i = start; i < end; i++) {
                if (!Character.isLetterOrDigit(source.charAt(i)) &&
                        !Character.toString(source.charAt(i)).equals(" ")) {
                    return "";
                }
            }
            return null;
        };

        view.validateEdittext(new InputFilter[]{filter, new InputFilter.LengthFilter(10)});
    }
}

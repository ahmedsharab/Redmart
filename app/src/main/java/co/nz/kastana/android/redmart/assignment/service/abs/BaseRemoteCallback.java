package co.nz.kastana.android.redmart.assignment.service.abs;


import retrofit.RetrofitError;

public abstract class BaseRemoteCallback<T> extends AbstractCallback<T> {

    protected abstract ErrorEvent getGenericErrorEvent(String message);
    protected abstract String getGenericErrorMessage();

    @Override
    protected void onFailure(RetrofitError error) {
        publish(getGenericErrorEvent(getGenericErrorMessage()));
    }
}

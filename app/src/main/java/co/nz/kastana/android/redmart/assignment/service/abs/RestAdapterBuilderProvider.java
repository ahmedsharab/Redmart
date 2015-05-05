package co.nz.kastana.android.redmart.assignment.service.abs;

import com.google.inject.Provider;

import retrofit.RestAdapter;

import static retrofit.RestAdapter.LogLevel.FULL;

public class RestAdapterBuilderProvider implements Provider<RestAdapter.Builder> {
    @Override
    public RestAdapter.Builder get() {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        return builder.setLogLevel(FULL);
    }
}
package co.nz.kastana.android.redmart.assignment.service.abs;

import com.google.inject.AbstractModule;

import retrofit.RestAdapter;

public class RemoteGuiceBindings extends AbstractModule {
    @Override
    protected void configure() {
        bind(RestAdapter.Builder.class)
                .toProvider(RestAdapterBuilderProvider.class)
                .asEagerSingleton();
    }
}

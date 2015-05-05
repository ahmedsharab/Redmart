package co.nz.kastana.android.redmart.assignment;

import com.google.inject.Inject;
import com.google.inject.Provider;

import java.util.concurrent.Executor;

import retrofit.RestAdapter;

public class TestRestAdapterBuilderProvider implements Provider<RestAdapter.Builder> {

    @Inject
    private TestClient client;

    @Override
    public RestAdapter.Builder get() {
        return new RestAdapter.Builder()
                .setExecutors(new Executor(){
                    @Override
                    public void execute(Runnable command) {
                        command.run();
                    }
                }, null)
                .setClient(client);
    }
}
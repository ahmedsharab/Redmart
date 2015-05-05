package co.nz.kastana.android.redmart.assignment.service.abs;

public interface RemoteOperation<E, C extends AbstractCallback> {
    public void execute(E endpoint, C callback);
}
package co.nz.kastana.android.redmart.assignment.activity;

import com.google.inject.Inject;

import co.nz.kastana.android.redmart.assignment.roboguice.EventBus;
import roboguice.activity.RoboActionBarActivity;

public class BaseActivity extends RoboActionBarActivity {
    @Inject
    protected EventBus eventBus;
}

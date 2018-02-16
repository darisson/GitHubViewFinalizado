package br.com.darisson.gitview.view.activity;


import android.widget.TextView;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;
import br.com.darisson.gitview.R;
import br.com.darisson.gitview.event.RequestFailedEvent;
import br.com.darisson.gitview.model.Owner;
import br.com.darisson.gitview.service.GitHubService;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewById(R.id.texto_busca)
    protected TextView editText;

    @Click(R.id.botao_busca)
    public void buscar(){
        GitHubService gitHubService = new GitHubService();
        gitHubService.requestUser(editText.getText().toString());
        showProgressDialog(this);
    }

    @Subscribe
    public void onEvent(Owner owner){
        RepositoryActivity_.intent(this).extra(RepositoryActivity.OWNER_EXTRA, owner).start();
        dismissProgressDialog();
    }

    @Subscribe
    public void onEvent(RequestFailedEvent failedEvent){
        /**
         *receber um evento que contem o objeto do RequestFailedEvent
         */

        String errorMsg = getString(R.string.err_usuaruio);
        if (failedEvent.isDefaultError()){
            if(failedEvent.getMessage().equals("")){
                errorMsg = getString(failedEvent.getErr_message());
            }else{
                errorMsg = failedEvent.getMessage();
            }

        }
        showAlertDialog(MainActivity.this, errorMsg,"erro");
        dismissProgressDialog();
    }
}


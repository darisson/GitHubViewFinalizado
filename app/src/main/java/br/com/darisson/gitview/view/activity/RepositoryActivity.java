package br.com.darisson.gitview.view.activity;


import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;
import java.util.List;
import br.com.darisson.gitview.R;
import br.com.darisson.gitview.adapter.RepositoryAdapter;
import br.com.darisson.gitview.model.Owner;
import br.com.darisson.gitview.model.Repository;
import br.com.darisson.gitview.service.GitHubService;

@EActivity(R.layout.activity_repository)
public class RepositoryActivity extends BaseActivity {

    public static final String OWNER_EXTRA = "owner";

    @ViewById(R.id.lista_recycler)
    protected RecyclerView recyclerView;

    @ViewById(R.id.repository_image)
    protected ImageView fotoUsuario;

    @ViewById(R.id.usuario_git)
    protected TextView usuario;

    @Bean
    protected RepositoryAdapter adapter;

    @AfterViews
    public void afterViews() {

        GitHubService gitHubService = new GitHubService();
        Owner owner = (Owner) getIntent().getSerializableExtra(OWNER_EXTRA);
        gitHubService.requestProfile(owner.getLogin());
        showProgressDialog(this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration
                (recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        Picasso.with(this).load(owner.getAvatarUrl()).into(fotoUsuario);
        usuario.setText(owner.getLogin());
    }

    /**
     *vou receber um evento que contem uma lista de repositorios
     */
    @Subscribe
    public void onEvent(List<Repository> list){
        /**
         *preencher os repositorios
         */
        adapter.setItems(list);
        recyclerView.setAdapter(adapter);
        dismissProgressDialog();
    }

    @Click(R.id.botao_voltar)
    public void Voltar(){
        onBackPressed();
    }
}


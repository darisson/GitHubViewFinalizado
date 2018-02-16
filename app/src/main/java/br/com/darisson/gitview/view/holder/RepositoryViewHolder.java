package br.com.darisson.gitview.view.holder;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import br.com.darisson.gitview.R;
import br.com.darisson.gitview.model.Repository;

@EViewGroup(R.layout.item_view_repository)
public class  RepositoryViewHolder extends RelativeLayout {

    @ViewById(R.id.text_repository)
    protected TextView tvRepository;

    @ViewById(R.id.text_language)
    protected TextView language;

    public RepositoryViewHolder(Context context) {
        super(context);
    }

    public void bind(Repository repository){
        tvRepository.setText(repository.getName());
        language.setText(repository.getLanguage());
    }

}

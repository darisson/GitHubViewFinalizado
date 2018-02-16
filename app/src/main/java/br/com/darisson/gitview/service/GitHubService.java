package br.com.darisson.gitview.service;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import java.util.List;

import br.com.darisson.gitview.R;
import br.com.darisson.gitview.event.RequestFailedEvent;
import br.com.darisson.gitview.model.Owner;
import br.com.darisson.gitview.model.Repository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubService extends BaseGitHubService {

    public void requestUser(String username){

        getGitHubAPI().getUser(username).enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {

                if (response.isSuccessful()){
                    EventBus.getDefault().post(response.body());
                }else{
                    EventBus.getDefault().post(new RequestFailedEvent(response.message(),false,R.string.err_message));
                }
            }
            @Override
            public void onFailure(Call<Owner> call, Throwable t) {
                EventBus.getDefault().post(new RequestFailedEvent("",true,R.string.err_message));
            }
        });
    }

    public void requestProfile(String username){
        /**
         *fazendo a solicitacao http para o servidor atraves da string passada na url
         */

        getGitHubAPI().getRepository(username).enqueue(new Callback<List<Repository>>(){
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                if (response.isSuccessful()){
                    EventBus.getDefault().post(response.body());
                }else{
                    EventBus.getDefault().post(new RequestFailedEvent(response.message(),false, R.string.err_message));
                }
            }
            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                EventBus.getDefault().post(new RequestFailedEvent("",true, R.string.err_message));

            }
        });
    }
}





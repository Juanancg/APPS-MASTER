package com.example.gitrepos.apirest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {
  @GET("users/{usuario}/repos")
  Call<List<Repositorio>> listRepos(@Path("usuario")String nombre);
}
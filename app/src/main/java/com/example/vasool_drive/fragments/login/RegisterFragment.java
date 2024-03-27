package com.example.vasool_drive.fragments.login;

import static com.example.vasool_drive.MyApplication.firebaseAuth;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vasool_drive.MainActivity;
import com.example.vasool_drive.MyApplication;
import com.example.vasool_drive.R;
import com.example.vasool_drive.databinding.FragmentRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.concurrent.Executor;


public class RegisterFragment extends Fragment {

    private static final String TAG = " REGISTER FRAGMENT " ;

    private FragmentRegisterBinding binding;

    public RegisterFragment() {
        // Required empty public constructor
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null){
            //reload();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.btnNext.setOnClickListener(v -> {

            String Name = binding.etName.getText().toString();
            String UserName = binding.etUserName.getText().toString();
            String Password = binding.etPassword.getText().toString();
            String ConfirmPassword = binding.etConfirmPassword.getText().toString();
            String EmailID = binding.etEmailID.getText().toString();
            String Mobile = binding.etMobile.getText().toString();

            if (Name.isEmpty() ){

                binding.etName.setError("Please Enter Name Not More than 20 letter's");

            }
            if (UserName.isEmpty() ){

                binding.etUserName.setError("Please Enter UserName Not More than 20 letter's");

            }
            if (Password.isEmpty() ){

                binding.etPassword.setError("Please Enter Password Not More than 20 letter's");

            }
            if (ConfirmPassword != Password|| ConfirmPassword.isEmpty()  ){

                binding.etConfirmPassword.setError("Enter Correct Password Not More than 20 letter's");

            }
            if (Mobile.isEmpty() || Mobile.length() < 10){

                binding.etMobile.setError("Please Enter Valid Mobile No ");

            }

            if (EmailID.isEmpty() ){

                binding.etEmailID.setError("Please Enter Email Address/ ID");

            }
            if (EmailID.isEmpty() || Password.isEmpty() || ConfirmPassword.isEmpty() || Name.isEmpty()|| UserName.isEmpty()|| Mobile.isEmpty()){
                return;
            }
            else {

                firebaseAuth.fetchSignInMethodsForEmail(EmailID)
                        .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                                boolean isNewUser = task.getResult().getSignInMethods().isEmpty();

                                if (isNewUser) {
                                    Log.e("TAG", "Is New User!");
                                    binding.progressCircular.setVisibility(View.VISIBLE);

                                    CreateNewAccount(EmailID, Password);

                                } else {

                                    binding.etEmailID.setError("This Email ID Already Used, Enter Another Email");

                                }

                            }
                        });

            }
        });

    }

    private void CreateNewAccount ( String email, String password){

        MyApplication.firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                binding.progressCircular.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(getView()).popBackStack(R.id.registerFragment,true);
                    Navigation.findNavController(getView()).navigate(R.id.loginFragment);
                } else {
                    Toast.makeText(getActivity(), "Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }







}
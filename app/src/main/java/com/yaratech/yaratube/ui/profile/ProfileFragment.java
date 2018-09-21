package com.yaratech.yaratube.ui.profile;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.soundcloud.android.crop.Crop;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.profile.imageprovider.ImageProviderFragment;
import com.yaratech.yaratube.util.Constant;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

import static android.app.Activity.RESULT_OK;
import static com.yaratech.yaratube.ui.profile.imageprovider.ImageProviderFragment.IMAGE_PROVIDER_TAG;


public class ProfileFragment extends Fragment implements ImageProviderFragment.OnImageProviderListener
        , ProfileContract.View {


    private Button buttonCalender;
    private PersianDatePickerDialog picker;
    private ImageView mImageView;
    File destination;
    String imageFilePath;
    private String dateOfBirth;
    private String gender;
    private Button buttonSave, buttonCancel;
    private final int CAMERA = 1;
    private static final int CAMERA_PERMISSION = 2;
    private static final int GALLERY_PERMISSION = 3;
    private Uri imageFileUri;
    private ProfileContract.Presenter presenter;


    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ProfilePresenter(getContext(), this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonCalender = view.findViewById(R.id.birth_date);
        mImageView = view.findViewById(R.id.profile_picture);
        buttonSave = view.findViewById(R.id.save);
        buttonCancel = view.findViewById(R.id.cancel);
        final EditText name = view.findViewById(R.id.name_family);
        Spinner dropdown = view.findViewById(R.id.user_gender);


        buttonCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendar();
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageProviderFragment imageProviderFragment = ImageProviderFragment.newInstance();
                FragmentManager fragmentManager = getChildFragmentManager();
                imageProviderFragment.show(fragmentManager, IMAGE_PROVIDER_TAG);
                // fromCamera();
            }
        });


        String[] items = new String[]{"آقا","خانم"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                switch (position) {
                    case 0:
                        gender = "Male";
                        break;
                    case 1:
                        gender = "Female";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                // no-op
            }
        });
        presenter.fillProfile(name, dropdown, buttonCalender, mImageView);


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // save data in database
                presenter.updateUserInDatabase(name.getText().toString(),
                        gender, buttonCalender.getText().toString(), imageFilePath);

                // send data to server
                presenter.sendProfileToServer(dateOfBirth, gender);

                // send profile image to server
                if (imageFilePath != null) {
                    // if user select an image for profile

                    File file = new File(imageFilePath);
                    long imageWidth = file.length() / 1024;
                    if (imageWidth > 1024) {
                    } else {
                        presenter.sendProfileImageToServer(file);
                    }
                }

                Constant.hideKeyboardFrom(getContext(), view);
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Constant.hideKeyboardFrom(getContext(), view);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }


    public void showCalendar() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "Shabnam-Light-FD.ttf");

        PersianCalendar initDate = new PersianCalendar();
        initDate.setPersianDate(1370, 5, 14);
//        picker.setInitDate(initDate);
        picker = new PersianDatePickerDialog(getContext())
                .setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setInitDate(initDate)
                .setActionTextColor(Color.GRAY)
                .setTypeFace(typeface)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        Toast.makeText(getContext(), persianCalendar.getPersianYear()
                                + "/" + persianCalendar.getPersianMonth() + "/"
                                + persianCalendar.getPersianDay(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDismissed() {

                    }
                });

        picker.show();
    }


    @Override
    public void openCamera() {
        if (!checkCameraPermissions(getContext())) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA},
                    CAMERA_PERMISSION);

        } else {
            getImageFromCamera();
        }
    }


    private static final String TAG = "ProfileFragment";

    public boolean checkCameraPermissions(Context context) {
        return ContextCompat.checkSelfPermission(context
                , Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(context
                , Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }


    private void getImageFromCamera() {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();

                imageFileUri = Uri.fromFile(new File(photoFile.getAbsolutePath()));
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            Uri photoUri = FileProvider.getUriForFile(getContext(),
                    getActivity().getPackageName() + ".provider",
                    photoFile);
            pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(pictureIntent, CAMERA);
        }
    }


    @Override
    public void openGallery() {
        if (!checkGalleryPermissions(getContext())) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    GALLERY_PERMISSION);
        } else
            Crop.pickImage(getContext(), this);

    }

    public boolean checkGalleryPermissions(Context context) {
        return ContextCompat.checkSelfPermission(context
                , Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }


    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permission", " granted");
                    getImageFromCamera();

                } else {
                    Log.e("permission", " denied");
                }
                return;
            case GALLERY_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permission", " granted");
                    Crop.pickImage(getContext(), this);

                } else {
                    Log.e("permission", " denied");
                }
                return;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == CAMERA) {

                beginCrop(imageFileUri);

            }
            //response of user select image from gallery
            else if (requestCode == Crop.REQUEST_PICK) {

                beginCrop(data.getData());

            }
            //response of user croped an image
            else if (requestCode == Crop.REQUEST_CROP) {
                String cropedImageFilePath = Crop.getOutput(data).getPath();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(
                            getContext().getContentResolver(),
                            Uri.fromFile(new File(cropedImageFilePath)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
                destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fileOutputStream = null;

                try {
                    destination.createNewFile();
                    fileOutputStream = new FileOutputStream(destination);
                    fileOutputStream.write(byteArrayOutputStream.toByteArray());
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.e("Tag", "resss" + destination.getPath());

                // because file name is always same
                RequestOptions requestOptions = new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true);
                Glide
                        .with(getContext())
                        .load(destination)
                        .apply(requestOptions)
                        .apply(RequestOptions.circleCropTransform())
                        .into(mImageView);
            }
        }
    }

    private void beginCrop(Uri source) {

        Uri destination = Uri.fromFile(new File(getContext().getCacheDir(), "cropped"));
        Crop.of(source, destination).withMaxSize(1024, 1024).asSquare().start(getContext(), this);
    }

    @Override
    public void toast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


//        private void openCameraIntent (Intent cameraIntent){
//            Intent pictureIntent = new Intent(
//                    MediaStore.ACTION_IMAGE_CAPTURE);
//            if (pictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//                //Create a file to store the image
//                File photoFile = null;
//                try {
//                    photoFile = createImageFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                if (photoFile != null) {
//                    Uri photoURI = FileProvider.getUriForFile(
//                            getContext(),
//                            getContext().getPackageName() + ".provider",
//                            photoFile);
//                    pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
//                            photoURI);
//                    Log.d("ATG", "openCameraIntent: " + photoURI.getPath());
//                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
//                }
//            }
//        }


}

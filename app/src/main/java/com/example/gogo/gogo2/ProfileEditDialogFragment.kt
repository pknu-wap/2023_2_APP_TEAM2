package com.example.gogo.gogo2

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.core.VideoCapture
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.gogo.MainViewModel
import com.example.gogo.databinding.FragmentDialogProfileEditBinding
import de.hdodenhof.circleimageview.CircleImageView
import java.io.ByteArrayOutputStream
import java.util.concurrent.ExecutorService


class ProfileEditDialogFragment : DialogFragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    interface ProfileImageListener {
        fun onProfileImageCaptured(imageUri: Uri)
    }

    private var profileImageListener: ProfileImageListener? = null

    fun setProfileImageListener(listener: ProfileImageListener) {
        profileImageListener = listener
    }

    private var _binding: FragmentDialogProfileEditBinding? = null
    private var currentImageUri: Uri? = null
    private val binding get() = _binding!!
    private lateinit var profileImageView: CircleImageView
    private val OPEN_GALLERY = 1
    private val TAKE_PICTURE = 2

    private val myPageViewModel: MyPageViewModel by activityViewModels()

    private val imageResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri = result.data?.data
            imageUri?.let {
                myPageViewModel.updateProfileImage(it)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view = inflater.inflate(R.layout.activity_dialog, container, false)
        _binding = FragmentDialogProfileEditBinding.inflate(inflater, container, false)
        Log.d("CustomDialog", "onCreateView")
        val view = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setDimAmount(0.7f)


        //profileImageView = view.findViewById(R.id.profileImage)
        //profileImageView = binding.profileImage
//        if (_binding == null) {
//            Log.e("ProfileEditDialog", "Binding is null")
//            return null
//        }
//
//
////        profileImageView = binding.profileImage
//
//        if (profileImageView == null) {
//            Log.e("ProfileEditDialog", "profileImageView is null")
//            return null
//        }

        //profileImageView = _binding?.profileImageView ?: return null

        binding.dialBtn1.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            mainViewModel.updateFragmentStatus(MainViewModel.PageType.CAMERA)
        }

        binding.dialBtn2.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*")
            imageResult.launch(intent)
//            Log.d("test","1")
//            selectGallery()
//            Log.d("test","2")
        }

//        binding.dialBtn2.setOnClickListener {
//            val intent = Intent(Intent.ACTION_PICK)
//            intent.type = "image/*"
//            activityResult.launch(intent)
//        }

        binding.dialBtn3.setOnClickListener {
            dismiss()
        }

//        binding.profileImage.setOnClickListener {
//            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            startActivityForResult(cameraIntent, TAKE_PICTURE)
//        }

        return view
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        when (requestCode) {
//            OPEN_GALLERY -> {
//                if (resultCode == Activity.RESULT_OK) {
//                    currentImageUri = data?.data
//                    setProfileImage(currentImageUri)
//                }
//            }
//
//            TAKE_PICTURE -> {
//                if (resultCode == Activity.RESULT_OK) {
//                    Log.d("test", "Tester")
//                    data?.data?.let { imageUri ->
//                        val imageBitmap = data?.extras?.get("data") as Bitmap?
//                        Log.d("uri", imageBitmap.toString())
//                        currentImageUri = getImageUri(requireContext(), imageBitmap)
//                        Log.d("uri", currentImageUri.toString())
//                        setProfileImage(currentImageUri)
//                    }
//                }
//            }
//        }
//    }


//    private fun setProfileImage(imageUri: Uri?) {
//        try {
////            Glide.with(requireContext())
////                .load(imageUri)
////                .into(profileImageView)
//            val bitmap = MediaStore.Images.Media.getBitmap(
//                requireContext().contentResolver,
//                imageUri
//            )
//
//            val fileName = "profile_picture.jpg"
//            saveBitmapToInternalStorage(bitmap, fileName)
//            profileImageView.setImageBitmap(bitmap)
//
//            Log.d("ProfileEditDialog", "onImageCaptured called with imageUri: $imageUri")
//            onImageCaptured(imageUri ?: return)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }

//    private fun setProfileImage(imageUri: Uri?) {
//        try {
//            val bitmap = MediaStore.Images.Media.getBitmap(
//                requireContext().contentResolver,
//                imageUri
//            )
//
//            val fileName = "profile_picture.jpg"
//            saveBitmapToInternalStorage(bitmap, fileName)
//            profileImageView.setImageBitmap(bitmap)
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }

//    private fun onImageCaptured(imageUri: Uri) {
//        profileImageListener?.onProfileImageCaptured(imageUri)
//        dismiss()
//        Log.d("ProfileEditDialog", "onImageCaptured called with imageUri: $imageUri")
//    }

//    private fun getImageUri(context: Context, bitmap: Bitmap?): Uri? {
//        val bytes = ByteArrayOutputStream()
//        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
//        val path = MediaStore.Images.Media.insertImage(
//            context.contentResolver,
//            bitmap,
//            "Title",
//            null
//        )
//
////        return Uri.parse(path)
//        return if (path != null) {
//            Uri.parse(path)
//        } else {
//            null
//        }
//    }

//    private fun saveBitmapToInternalStorage(bitmap: Bitmap, fileName: String) {
//        try {
//            val bytes = ByteArrayOutputStream()
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
//            val fos = requireContext().openFileOutput(fileName, Context.MODE_PRIVATE)
//            fos.write(bytes.toByteArray())
//            fos.close()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }

    override fun onDestroyView() {
//        Log.d("CustomDialog", "onDestroyView")
        super.onDestroyView()
        _binding = null
    }

    private fun getRealPathFromURI(uri: Uri): String {
        val buildName = Build.MANUFACTURER
        if (buildName.equals("Xiaomi")) {
            return uri.path!!
        }
        var columnIndex = 0
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = requireActivity().contentResolver.query(uri, proj, null, null, null)
        if (cursor!!.moveToFirst()) {
            columnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }
        val result = cursor.getString(columnIndex)
        cursor.close()
        return result
    }

    private fun selectGallery() {
        val writePermission = ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val readPermission = ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        if (writePermission == PackageManager.PERMISSION_DENIED ||
            readPermission == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                REQ_GALLERY
            )
        } else {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*")
            imageResult.launch(intent)
        }
    }


    companion object {
        const val DIALOG_TAG = "ProfileEditDialog"

        const val REVIEW_MIN_LENGTH = 10
        const val REQ_GALLERY = 1

        const val PARAM_KEY_IMAGE = "image"
        const val PARAM_KEY_PRODUCT_ID = "product_id"
        const val PARAM_KEY_REVIEW = "review_content"
        const val PARAM_KEY_RATING = "rating"
    }
}
//package com.example.gogo.gogo2
//
////import android.Manifest
////import android.content.ContentValues
////import android.content.pm.PackageManager
////import android.os.Build
////import android.os.Bundle
////import android.provider.MediaStore
////import androidx.appcompat.app.AppCompatActivity
////import androidx.camera.core.ImageCapture
////import androidx.camera.video.Recorder
////import androidx.camera.video.Recording
////import androidx.camera.video.VideoCapture
////import androidx.core.app.ActivityCompat
////import androidx.core.content.ContextCompat
//////import com.android.example.cameraxapp.databinding.ActivityCameraNewBinding
////import java.util.concurrent.ExecutorService
////import java.util.concurrent.Executors
////import android.widget.Toast
////import androidx.activity.result.contract.ActivityResultContracts
////import androidx.camera.lifecycle.ProcessCameraProvider
////import androidx.camera.core.Preview
////import androidx.camera.core.CameraSelector
////import android.util.Log
////import androidx.camera.core.ImageAnalysis
////import androidx.camera.core.ImageCaptureException
////import androidx.camera.core.ImageProxy
////import androidx.camera.video.FallbackStrategy
////import androidx.camera.video.MediaStoreOutputOptions
////import androidx.camera.video.Quality
////import androidx.camera.video.QualitySelector
////import androidx.camera.video.VideoRecordEvent
////import androidx.core.content.PermissionChecker
////import com.example.gogo2.databinding.ActivityCameranewBinding
////import java.nio.ByteBuffer
////import java.text.SimpleDateFormat
////import java.util.Locale
//////import android.Manifest
////
////typealias LumaListener = (luma: Double) -> Unit
////
////class CameraNewActivity : AppCompatActivity() {
////    private lateinit var viewBinding: ActivityCameranewBinding
////
////    private var imageCapture: ImageCapture? = null
////
////    private var videoCapture: VideoCapture<Recorder>? = null
////    private var recording: Recording? = null
////
////    private lateinit var cameraExecutor: ExecutorService
////
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        viewBinding = ActivityCameranewBinding.inflate(layoutInflater)
////        setContentView(viewBinding.root)
////
////        // Request camera permissions
////        if (allPermissionsGranted()) {
////            startCamera()
////        } else {
////            requestPermissions()
////        }
////
////        // Set up the listeners for take photo and video capture buttons
////        viewBinding.imageCaptureButton.setOnClickListener { takePhoto() }
////        viewBinding.videoCaptureButton.setOnClickListener { captureVideo() }
////
////        cameraExecutor = Executors.newSingleThreadExecutor()
////    }
////
////    private fun takePhoto() {}
////
////    private fun captureVideo() {}
////
////    private fun startCamera() {}
////
////    private fun requestPermissions() {}
////
////    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
////        ContextCompat.checkSelfPermission(
////            baseContext, it) == PackageManager.PERMISSION_GRANTED
////    }
////
////    override fun onDestroy() {
////        super.onDestroy()
////        cameraExecutor.shutdown()
////    }
////
////    companion object {
////        private const val TAG = "CameraXApp"
////        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
////        private val REQUIRED_PERMISSIONS =
////            mutableListOf (
////                Manifest.permission.CAMERA,
////                Manifest.permission.RECORD_AUDIO
////            ).apply {
////                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
////                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
////                }
////            }.toTypedArray()
////    }
////}
////
////private val REQUIRED_PERMISSIONS = arrayOf(
////    Manifest.permission.CAMERA,
////    Manifest.permission.WRITE_EXTERNAL_STORAGE
////    // 다른 필요한 권한들을 여기에 추가하세요
////)
////
////private val activityResultLauncher =
////    registerForActivityResult(
////        ActivityResultContracts.RequestMultiplePermissions())
////    { permissions ->
////        // Handle Permission granted/rejected
////        var permissionGranted = true
////        permissions.entries.forEach {
////            if (it.key in REQUIRED_PERMISSIONS && it.value == false)
////            {
////                permissionGranted = false
////            }
////        }
////        if (!permissionGranted) {
////            Toast.makeText(this,
////                "Permission request denied",
////                Toast.LENGTH_SHORT).show()
////        } else {
////            startCamera()
////        }
////    }
//
//
//import android.Manifest
//import android.content.ContentValues
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.icu.text.SimpleDateFormat
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.os.Environment
//import android.provider.MediaStore
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.camera.core.CameraSelector
//import androidx.camera.core.ImageCapture
//import androidx.camera.core.ImageCaptureException
//import androidx.camera.core.Preview
//import androidx.camera.lifecycle.ProcessCameraProvider
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.engine.DiskCacheStrategy
//import com.example.gogo.MainViewModel
//import com.example.gogo.R
//import com.example.gogo.databinding.ActivityCameranewBinding
//import de.hdodenhof.circleimageview.CircleImageView
//import java.io.File
//import java.util.Locale
//import java.util.concurrent.ExecutorService
//import java.util.concurrent.Executors
//
////import com.example.gogo2.databinding.FragmentCameraNewBinding
//
//
//class CameraNewFragment : Fragment() {
//    private var _viewBinding: ActivityCameranewBinding? = null
//    private val viewBinding get() = _viewBinding!!
//
//    private lateinit var cameraExecutorService: ExecutorService
//    private lateinit var imageCapture: ImageCapture
//
//    private val TAG = "CameraNewFragment"
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _viewBinding = ActivityCameranewBinding.inflate(inflater, container, false)
//        return viewBinding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        cameraExecutorService = Executors.newSingleThreadExecutor()
//
//        // 카메라 권한 요청
//        requestCameraPermissions()
//
//        // 카메라 초기화 및 뷰 설정
//        initCamera()
//
//        // 이미지 캡처 버튼 클릭 리스너 설정
//        viewBinding.imageCaptureButton.setOnClickListener { takePhoto() }
//
//        // 뒤로가기 버튼 클릭 시 리스너 설정
//        viewBinding.backButton.setOnClickListener { onBackPressed() }
//    }
//
////    fun onProfileImageCaptured(imageUri: Uri) {
////        savedImageAndMyPageFragment(imageUri)
////    }
////
////    private fun savedImageAndMyPageFragment(imageUri: Uri) {
////        val myPageFragment = parentFragmentManager.findFragmentByTag(MyPageFragment.TAG) as? MyPageFragment
////        myPageFragment?.updateProfileImage(imageUri)
////    }
//
//
//    private val REQUIRED_PERMISSIONS = arrayOf(
//        Manifest.permission.CAMERA,
//        // 다른 필요한 권한들을 여기에 추가하세요  - ???
//    )
//    private fun initCamera() {
//        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
//        cameraProviderFuture.addListener({
//            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
//
//            val preview = Preview.Builder().build().also {
//                it.setSurfaceProvider(viewBinding.viewFinder.surfaceProvider)
//            }
//
//            imageCapture = ImageCapture.Builder().build()
//
//            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//
//            try {
//                cameraProvider.unbindAll()
//                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }, ContextCompat.getMainExecutor(requireContext()))
//    }
//
////    private fun takePhoto() {
////        val imageCapture = imageCapture ?: return
////
////        val photoFile = createTempFile("photo", ".jpg")
////        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
////
////        imageCapture.takePicture(
////            outputOptions, ContextCompat.getMainExecutor(this), object : ImageCapture.OnImageSavedCallback {
////                override fun onError(error: ImageCaptureException) {
////                    error.printStackTrace()
////                    Toast.makeText(baseContext, "사진을 찍을 수 없습니다.", Toast.LENGTH_SHORT).show()
////                }
////
////                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
////                    Toast.makeText(baseContext, "사진이 저장되었습니다: ${photoFile.absolutePath}", Toast.LENGTH_SHORT).show()
////                }
////            })
////    }
//
//    private fun requestCameraPermissions() {
//        when {
//            isPermissionsGranted() -> {
//                // 권한이 이미 허용된 경우 카메라를 초기화합니다.
//                initCamera()
//            }
//            else -> {
//                // 권한을 요청합니다.
//                requestPermissionLauncher.launch(REQUIRED_PERMISSIONS)
//            }
//        }
//    }
//
//    private fun isPermissionsGranted() =
//        REQUIRED_PERMISSIONS.all {
//            ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
//        }
//
//    private val requestPermissionLauncher =
//        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
//            if (permissions.all { it.value }) {
//                // 모든 권한이 허용된 경우 카메라를 초기화합니다.
//                initCamera()
//            } else {
//                Toast.makeText(requireContext(), "권한 요청이 거부되었습니다.", Toast.LENGTH_SHORT).show()
//                requireActivity().finish()
//            }
//        }
//
//    private fun takePhoto() {
//
//        val imageCapture = imageCapture ?: return
//        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
//            .format(System.currentTimeMillis())
//
//        val contentValues = ContentValues().apply {
//            put(MediaStore.Images.Media.DISPLAY_NAME, name)
//            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
//            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
//                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
//            }
//        }
//
//        val outputDirectory = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            File(requireContext().externalMediaDirs.firstOrNull(), "CameraX-Image")
//        } else {
//            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//        }
//
//        outputDirectory?.mkdirs()
//
//        val photoFile = File(outputDirectory, "$name.jpg")
//        val outputOptions = ImageCapture.OutputFileOptions
//            .Builder(photoFile)
//            .build()
//
//
//        imageCapture.takePicture(
//            outputOptions,
//            ContextCompat.getMainExecutor(requireContext()),
//            object : ImageCapture.OnImageSavedCallback {
//                override fun onError(exc: ImageCaptureException) {
//                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
//                }
//
//
//                override fun onImageSaved(output: ImageCapture.OutputFileResults){
//                    val savedUri = output.savedUri ?: Uri.fromFile(photoFile)
//                    val msg = "Photo capture succeeded: ${output.savedUri}"
//                    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
//                    Log.d(TAG, msg)
//
////                    val myPageFragment =
////                        parentFragmentManager.findFragmentByTag(MyPageFragment.TAG) as? MyPageFragment
////                    myPageFragment?.updateProfileImage(savedUri)
//
//                    savedPhotoToGooglePhotos(savedUri)
//
////                    loadSavedImage(savedUri)
//                    val mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
//                    mainViewModel.updateProfileImageUri(savedUri)
//
//                }
//            }
//        )
//    }
//
//
//    private fun savedPhotoToGooglePhotos(photoUri: Uri) {
//        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, photoUri)
//        requireContext().sendBroadcast(mediaScanIntent)
//    }
//
////    override fun onBackPressed() {
////        super.onBackPressed()
////        // 뒤로가기 버튼을 눌렀을 때 layout_mypage로 돌아가도록 설정
////        startActivity(Intent(this, MyPageFragment::class.java))
////        requireActivity().finish()
////    }
//
//    private fun loadSavedImage(uri: Uri) {
//        val profileImageView: CircleImageView = requireView().findViewById(R.id.profileImage)
//
//        Glide.with(this)
//            .load(uri)
//            .skipMemoryCache(true)
//            .diskCacheStrategy(DiskCacheStrategy.NONE)
//            .into(profileImageView)
//    }
//
//    private fun onBackPressed() {
//        requireActivity().onBackPressed()
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _viewBinding = null
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        cameraExecutorService.shutdown()
//    }
//
//    companion object {
//        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
//    }
//
//}
//
//
//
////    private fun startCamera() {
////        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
////
////        cameraProviderFuture.addListener({
////            // Used to bind the lifecycle of cameras to the lifecycle owner
////            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
////
////            // Preview
////            val preview = Preview.Builder()
////                .build()
////                .also {
////                    it.setSurfaceProvider(viewBinding.viewFinder.surfaceProvider)
////                }
////
////            // Select back camera as a default
////            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
////
////            try {
////                // Unbind use cases before rebinding
////                cameraProvider.unbindAll()
////
////                // Bind use cases to camera
////                cameraProvider.bindToLifecycle(
////                    this, cameraSelector, preview)
////
////            } catch(exc: Exception) {
////                Log.e(TAG, "Use case binding failed", exc)
////            }
////
////        }, ContextCompat.getMainExecutor(requireContext()))
////    }

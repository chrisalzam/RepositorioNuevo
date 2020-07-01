package com.example.urbandictionaryapp

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.urbandictionaryapp.databinding.ActivityMainBinding
import com.example.urbandictionaryapp.presentation.MainViewModel
import com.example.urbandictionaryapp.presentation.RVDefinitionAdapter
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    //    private lateinit var myAdapter: MyRecyclerViewAdapter
    private lateinit var myAdapter: RVDefinitionAdapter
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var layoutBinding: ActivityMainBinding
    private lateinit var player: SimpleExoPlayer
    private var playWhenReady = false
    private var currentWindow = 0
    private var playbackPosition: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        setBinding()
        viewModel.getDefinitions()
        wireOnPropertyChanged()
        fillRecyclerView()

    }

    private fun initializePlayer() {
        Timber.d("MainActivity_TAG: initializePlayer: ")
        player = ExoPlayerFactory.newSimpleInstance(applicationContext);
        layoutBinding.epSound.player = player
    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onStop() {
        super.onStop()

        releasePlayer()
    }
//region ExoPlayer Events
//    override fun onResume() {
//        super.onResume()
//        Timber.d("MainActivity_TAG: onResume: ")
//
//        //hideSystemUi();
//        if ((Util.SDK_INT < 24 || player == null)) {
//            initializePlayer();
//        }
//
//    }

//    @SuppressLint("InlinedApi")
//    private fun hideSystemUi() {
//        layoutBinding.epSound.setSystemUiVisibility(
//            View.SYSTEM_UI_FLAG_LOW_PROFILE
//                    or View.SYSTEM_UI_FLAG_FULLSCREEN
//                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//        )
//    }

//    override fun onPause() {
//        super.onPause()
//        if (Util.SDK_INT < 24) {
//            releasePlayer();
//        }
//    }
//
//    override fun onStop() {
//        super.onStop()
//        if (Util.SDK_INT >= 24) {
//            releasePlayer();
//        }
//    }


    //endregion

    private fun setBinding() {
        Timber.d("MainActivity_TAG: setBinding: ")
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        layoutBinding.lifecycleOwner = this
        layoutBinding.viewModel = viewModel
    }

    private fun fillRecyclerView() {
        Timber.d("MainActivity_TAG: fillRecyclerView: ")
        /*myAdapter = MyRecyclerViewAdapter {
        }
        myAdapter.itemList = viewModel.availableDefinitions*/
        myAdapter =
            RVDefinitionAdapter { _, definition ->
                Timber.d("MainActivity_TAG: fillRecyclerView: OnDefinitionClicked ${definition.id}")
                preparePlayer(definition.soundUrls.first())
                playWhenReady = true
            }

        myAdapter.itemList = emptyList()

        layoutBinding.rvDefinitions.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        }
    }

    private fun preparePlayer(sound: String) {
        Timber.d("MainActivity_TAG: preparePlayer: PLAYING $sound")
        val uri = Uri.parse(sound)
        val mediaSource = buildMediaSource(uri)

        //player.seekTo(currentWindow, playbackPosition)
        player.prepare(mediaSource)
        player.playWhenReady = playWhenReady
    }

    private fun buildMediaSource(uri: Uri): MediaSource? {
        val dataSourceFactory: DataSource.Factory =
            DefaultDataSourceFactory(
                applicationContext,
                Util.getUserAgent(applicationContext, application.packageName)
            )
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }

    private fun releasePlayer() {
        Timber.d("MainActivity_TAG: releasePlayer: PLAYING IF PLAYER != NULL")
        //playWhenReady = player.playWhenReady
        player.release()
    }

    private fun wireOnPropertyChanged() {
        Timber.d("MainActivity_TAG: wireOnPropertyChanged: ")

        viewModel.onPropertyChanged(BR.availableDefinitions) {
            Timber.d("MainActivity_TAG: wireOnPropertyChanged: availableDefinitions: ${viewModel.availableDefinitions.size}")
            //populate a recyclerview
            myAdapter.itemList = viewModel.recyclerItemsViewModel
            viewModel.loading = false
        }

        viewModel.onPropertyChanged(BR.term) {
            Timber.d("MainActivity_TAG: wireOnPropertyChanged: term: ${viewModel.term}")
            viewModel.loading = true
            viewModel.getDefinitions()
        }

    }

}
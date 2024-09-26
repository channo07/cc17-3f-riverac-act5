package com.example.artspacerivera

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.artspacerivera.R
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var artworkImageView: ImageView
    private lateinit var artworkTitleTextView: TextView
    private lateinit var artworkAuthorTextView: TextView
    private lateinit var artworkDescriptionTextView: TextView
    private lateinit var previousButton: MaterialButton
    private lateinit var nextButton: MaterialButton

    private val artworks = listOf(
        Artwork(
            R.drawable.download__4_,
            "Bamboos and House",
            "Japanese arts",
            "Showcasing the local house and the bamboo that is near the house."
        ),
        Artwork(
            R.drawable.download__6_,
            "Komo girl",
            "Japanese arts",
            "Showcasing the outfit of the Japanese girls and the accessories that they have."
        ),
        Artwork(
            R.drawable.download__8_,
            "Japanese waves",
            "Japanese arts",
            "The Japanese waves is sacred to them that it became arts."
        ),
        Artwork(
            R.drawable.download__7_,
            "Temples",
            "Japanese arts",
            "Japanese temples are sacred to them."
        ),
        Artwork(
            R.drawable.download__5_,
            "Yin-Yang",
            "Japanese arts",
            "Yin-Yang symbolizes pure and sin in the Japanese."
        )
    )

    private var currentArtworkIndex = 0
    private var isFirstArtwork: Boolean = true
    private var isLastArtwork: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        artworkImageView = findViewById(R.id.artworkImageView)
        artworkTitleTextView = findViewById(R.id.artworkTitleTextView)
        artworkAuthorTextView = findViewById(R.id.artworkAuthorTextView)
        artworkDescriptionTextView = findViewById(R.id.artworkDescriptionTextView)
        previousButton = findViewById(R.id.previousButton)
        nextButton = findViewById(R.id.nextButton)

        updateArtworkDisplay()
        updateNavigationButtons()

        previousButton.setOnClickListener {
            navigateArtwork(false)
        }

        nextButton.setOnClickListener {
            navigateArtwork(true)
        }
    }

    private fun navigateArtwork(goForward: Boolean) {
        currentArtworkIndex = when {
            goForward -> (currentArtworkIndex + 1) % artworks.size
            else -> (currentArtworkIndex - 1 + artworks.size) % artworks.size
        }
        updateArtworkDisplay()
        updateNavigationButtons()
    }

    private fun updateArtworkDisplay() {
        val currentArtwork = artworks[currentArtworkIndex]
        artworkImageView.setImageResource(currentArtwork.imageResourceId)
        artworkTitleTextView.text = currentArtwork.title
        artworkAuthorTextView.text = currentArtwork.author
        artworkDescriptionTextView.text = currentArtwork.description
    }

    private fun updateNavigationButtons() {
        isFirstArtwork = currentArtworkIndex == 0
        isLastArtwork = currentArtworkIndex == artworks.size - 1

        previousButton.isEnabled = !isFirstArtwork
        nextButton.isEnabled = !isLastArtwork

        previousButton.alpha = if (isFirstArtwork) 0.5f else 1.0f
        nextButton.alpha = if (isLastArtwork) 0.5f else 1.0f
    }
}

data class Artwork(
    val imageResourceId: Int,
    val title: String,
    val author: String,
    val description: String
)
package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

// Clase simple que representa una obra de arte
data class Artwork(
    val imageResourceId: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Composable
fun ArtSpaceApp() {
    // Lista de obras de arte. Reemplaza los drawables por tus propias imagenes.
    val artworks = listOf(
        Artwork(R.drawable.imagen_art, "Sin título", "Autor desconocido", "S/F"),
        Artwork(R.drawable.art_2, "Mona Lisa", "Leonardo da Vinci", "1503"),
        Artwork(R.drawable.art_3, "La noche estrellada", "Vincent van Gogh", "1889")
    )

    // Estado que guarda el indice de la obra actual (empieza en 0)
    var currentIndex by remember { mutableIntStateOf(0) }
    val currentArtwork = artworks[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Seccion 1: Muro con la obra de arte
        ArtworkWall(
            imageResourceId = currentArtwork.imageResourceId,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Seccion 2: Descriptor de la obra
        ArtworkDescriptor(
            title = currentArtwork.title,
            artist = currentArtwork.artist,
            year = currentArtwork.year
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Seccion 3: Controlador de pantalla (botones Previous / Next)
        DisplayController(
            onPreviousClick = {
                currentIndex = when (currentIndex) {
                    0 -> artworks.size - 1
                    else -> currentIndex - 1
                }
            },
            onNextClick = {
                currentIndex = when (currentIndex) {
                    artworks.size - 1 -> 0
                    else -> currentIndex + 1
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// Seccion 1: Muro con la obra de arte
@Composable
fun ArtworkWall(imageResourceId: Int, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Gray),
        shadowElevation = 8.dp
    ) {
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.padding(24.dp)
        )
    }
}

// Seccion 2: Descriptor de la obra (titulo, artista, anio)
@Composable
fun ArtworkDescriptor(
    title: String,
    artist: String,
    year: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xFFECEBF4))
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Light
        )
        Row {
            Text(
                text = artist,
                fontWeight = FontWeight.Bold
            )
            Text(text = " ($year)")
        }
    }
}

// Seccion 3: Controlador de pantalla (botones Previous / Next)
@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier.width(120.dp)
        ) {
            Text(text = "Previous")
        }
        Button(
            onClick = onNextClick,
            modifier = Modifier.width(120.dp)
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}
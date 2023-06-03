package com.miko.consultaimei

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miko.consultaimei.ui.theme.ConsultaimeiTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkTheme = remember { mutableStateOf(false) }
            val icon: (@Composable () -> Unit)? = if (isDarkTheme.value) {
                {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }
            } else {
                null
            }

            ConsultaimeiTheme(darkTheme = isDarkTheme.value) {
                LandingPage(isDarkTheme,icon)


            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val isDarkTheme = remember { mutableStateOf(false) }

    val icon: (@Composable () -> Unit)? = if (isDarkTheme.value) {
        {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    } else {
        null
    }
    ConsultaimeiTheme(darkTheme = isDarkTheme.value) {
        //Greeting("Android")
        LandingPage(isDarkTheme,icon)
    }
}

@Composable
fun LandingPage(isDarkTheme: MutableState<Boolean>, icon: @Composable() (() -> Unit)?) {

    MaterialTheme() {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.onBackground
            )

        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = 40.dp,
                    vertical = 150.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                ElevatedCard(modifier=Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        /*
                        Switch(
                            checked = isDarkTheme.value,
                            onCheckedChange = { isDarkTheme.value = it },
                            colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colorScheme.primary)
                        )
                         */
                        Switch(
                            modifier = Modifier.semantics { contentDescription = "Demo with icon" },
                            checked = isDarkTheme.value,
                            onCheckedChange = { isDarkTheme.value = it },
                            thumbContent = icon
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.logo_osiptel),
                            contentDescription = "Logo",
                            modifier = Modifier.size(200.dp)
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = "Checa tu IMEI",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        ElevatedButton(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors( MaterialTheme.colorScheme.primary)) {
                            /*
                            Icon(
                                imageVector = Icons.Filled.Send,
                                contentDescription = "Favorite Icon",
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                             */
                            Text(text = "Empezar", color = MaterialTheme.colorScheme.onPrimary)
                        }

                    }

                }
            }
        }
    }

}
////////////////////////////////////////////////////////////////////////////////////////

@Preview(showBackground = true)
@Composable
fun HomeConsultaImeiPreview() {
    val isDarkTheme = remember { mutableStateOf(false) }
    val checkedQuestion = remember { mutableStateOf(false) }
    val textTexfield = rememberSaveable { mutableStateOf("") }

    val icon: (@Composable () -> Unit)? = if (isDarkTheme.value) {
        {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    } else {
        null
    }
    ConsultaimeiTheme(darkTheme = isDarkTheme.value) {
        //Greeting("Android")
        HomeConsultaImei(isDarkTheme,icon,checkedQuestion,textTexfield)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeConsultaImei(
    isDarkTheme: MutableState<Boolean>,
    icon: @Composable() (() -> Unit)?,
    checkedQuestion: MutableState<Boolean>,
    textTexfield: MutableState<String>,

    ){
    MaterialTheme() {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.onBackground
            )

        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = 20.dp,
                    vertical = 20.dp)
            ) {
                ElevatedCard(modifier=Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),

                        //horizontalArrangement = Arrangement.Center
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        /*
                        Switch(
                            checked = isDarkTheme.value,
                            onCheckedChange = { isDarkTheme.value = it },
                            colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colorScheme.primary)
                        )
                         */

                        //SWITCH
                        Switch(
                            modifier = Modifier.semantics { contentDescription = "Demo with icon" },
                            checked = isDarkTheme.value,
                            onCheckedChange = { isDarkTheme.value = it },
                            thumbContent = icon
                        )
                        //ICON BUTTON
                        IconToggleButton(
                            checked = checkedQuestion.value,
                            onCheckedChange = { checkedQuestion.value = it },
                        ) {
                            if (checkedQuestion.value) {
                                Icon(Icons.Filled.Info, contentDescription = "Localized description")
                            } else {
                                Icon(Icons.Outlined.Info, contentDescription = "Localized description")
                            }
                        }
                        //Image como logo
                        ImageAsIcon(
                            imageResId = R.drawable.solicitud,
                            contentDescription = "My Image",
                            size = 40.dp,
                            modifier = Modifier
                                .padding(5.dp)
                                .clickable { /* Acción al hacer clic en el icono */ }
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),

                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))

                        Image(
                            painter = painterResource(id = R.drawable.logo_osiptel),
                            contentDescription = "Logo",
                            modifier = Modifier.size(200.dp)
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = MaterialTheme.colorScheme.primary)) {
                                    Text(
                                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
                                        text = "Este aplicativo permite consultar en línea el código IMEI ",
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))

                        //TextField(value = textTexfield , onValueChange = textTexfield.value)
                        //TextField(value = textTexfield.value, onValueChange = {textTexfield.value = it},placeholder = { Text("Consultar IMEI") }, )
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = textTexfield.value,
                            onValueChange = { textTexfield.value = it },
                            //label = { Text("Label") } ,
                            placeholder = { Text("Consultar IMEI ") },
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            OutlinedCard(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Box(modifier = Modifier
                                    .fillMaxWidth()

                                ) {
                                    Row(modifier = Modifier.padding(10.dp)) {
                                        val checkedState = remember { mutableStateOf(true) }

                                        Checkbox(
                                            checked = checkedState.value,
                                            onCheckedChange = { checkedState.value = it }
                                        )
                                        Text(
                                            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
                                            text = "No soy un robot ",
                                            //color = Color.White
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Row() {
                            ElevatedButton(
                                modifier = Modifier.weight(1f),
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors( MaterialTheme.colorScheme.primary)) {
                                /*
                                Icon(
                                    imageVector = Icons.Filled.Send,
                                    contentDescription = "Favorite Icon",
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                                 */
                                Text(

                                    text = "Verificar",
                                    color = MaterialTheme.colorScheme.onPrimary)
                            }
                        }
                    }
                }
            }
        }
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////
@Preview(showBackground = true)
@Composable
fun PreguntasImeiPreview() {
    val isDarkTheme = remember { mutableStateOf(false) }
    val checkedQuestion = remember { mutableStateOf(false) }
    val textTexfield = rememberSaveable { mutableStateOf("") }

    val icon: (@Composable () -> Unit)? = if (isDarkTheme.value) {
        {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    } else {
        null
    }
    ConsultaimeiTheme(darkTheme = isDarkTheme.value) {
        //Greeting("Android")
        PreguntasImei(isDarkTheme,icon,checkedQuestion,textTexfield)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreguntasImei(
    isDarkTheme: MutableState<Boolean>,
    icon: @Composable() (() -> Unit)?,
    checkedQuestion: MutableState<Boolean>,
    textTexfield: MutableState<String>,

    ){
    MaterialTheme() {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.onBackground
            )

        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = 20.dp,
                    vertical = 20.dp)
            ) {
                ElevatedCard(modifier=Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),

                        //horizontalArrangement = Arrangement.Center
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        /*
                        Switch(
                            checked = isDarkTheme.value,
                            onCheckedChange = { isDarkTheme.value = it },
                            colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colorScheme.primary)
                        )
                         */

                        //SWITCH
                        Switch(
                            modifier = Modifier.semantics { contentDescription = "Demo with icon" },
                            checked = isDarkTheme.value,
                            onCheckedChange = { isDarkTheme.value = it },
                            thumbContent = icon
                        )
                        //ICON BUTTON
                        IconToggleButton(
                            checked = checkedQuestion.value,
                            onCheckedChange = { checkedQuestion.value = it },
                        ) {
                            if (checkedQuestion.value) {
                                Icon(Icons.Filled.Info, contentDescription = "Localized description")
                            } else {
                                Icon(Icons.Outlined.Info, contentDescription = "Localized description")
                            }
                        }
                        //Image como logo
                        ImageAsIcon(
                            imageResId = R.drawable.solicitud,
                            contentDescription = "My Image",
                            size = 40.dp,
                            modifier = Modifier
                                .padding(5.dp)
                                .clickable { /* Acción al hacer clic en el icono */ }
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),

                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))

                        Image(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "Logo",
                            modifier = Modifier.size(200.dp)
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = MaterialTheme.colorScheme.primary)) {
                                    Text(
                                        modifier = Modifier.padding(top = 10.dp, bottom = 15.dp, start = 16.dp, end = 16.dp),
                                        text = "La respuesta a su consulta es :",

                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        style = TextStyle(fontSize = 20.sp)

                                    )

                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))


                        Row(modifier = Modifier.fillMaxWidth()) {
                            OutlinedCard(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Box(modifier = Modifier
                                    .fillMaxWidth()

                                ) {
                                    Column() {

                                        Row(modifier = Modifier.fillMaxWidth(),Arrangement.Start) {
                                            Text(
                                                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
                                                text ="El número :",
                                                //color = MaterialTheme.colorScheme.onPrimary
                                            )
                                        }

                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center

                                        ) {
                                            Text(
                                                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
                                                text = "1234567891011",

                                                fontWeight = FontWeight.Bold,
                                                //color = Color(android.graphics.Color.parseColor("#599BCC")),
                                                //color = Color(android.graphics.Color.parseColor("#599BCC")),
                                                style = TextStyle(fontSize = 23.sp)
                                            )
                                        }

                                        Text(
                                            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),

                                            text = "El IMEI no se encuentra asociado a ningún terminal reportado",
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                        Spacer(modifier = Modifier.height(20.dp))
                                        
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Row() {
                            ElevatedButton(
                                modifier = Modifier.weight(1f),
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors( MaterialTheme.colorScheme.primary)
                            ) {

                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Favorite Icon",
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )

                                Text(
                                    modifier = Modifier.padding(start = 10.dp),
                                    text = "Regresar",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = TextStyle(fontSize = 18.sp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
/////////////////////////////////////////////////////////////////////////////////////////////////
@Preview (showBackground = true)
@Composable
fun PreviewPreguntasFrecuentes(){
    ScreenPreguntasFrecuentas()

}
val datos: List<DataApp> = listOf(
    DataApp("PrimeroPrimeroPrimeroPrimeroPrimeroPrimeroPrimero", "Subtitulo 1"),
    DataApp("Segundo", "SubtituloSubtituloSubtituloSubtituloSubtituloSubtitulo 2"),
    DataApp("Tercero", "Subtitulo 3"),
    DataApp("Cuarto", "Subtitulo 4"),
    DataApp("Quinto", "Subtitulo 5"),
    DataApp("Sexto", "SubtituloSubtituloSubtituloSubtituloSubtitulo 6"),
    DataApp("Septimo", "Subtitulo 7"),
    DataApp("Octavo", "Subtitulo 8"),
    DataApp("Noveno", "Subtitulo 9"),
    DataApp("Décimo", "Subtitulo 10"),
    DataApp("Onceavo", "Subtitulo 11"),
    DataApp("Doceavo", "Subtitulo 12"),
    DataApp("Treceavo", "Subtitulo 13")
)


@Composable
fun ScreenPreguntasFrecuentas(){
    Column() {
        textoPrincipalPreguntasFrecuentes()
        Spacer(modifier = Modifier.height(50.dp))
        ImagePrincipalPreguntasFrecuents()
        Spacer(modifier = Modifier.height(50.dp))
        ListaPreguntasFrecuentes(datos = datos)
        Spacer(modifier = Modifier.height(300.dp))
    }

}

@Composable
fun textoPrincipalPreguntasFrecuentes(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
            text = "Preguntas frecuentes",

            fontWeight = FontWeight.Bold,
            color = Color(android.graphics.Color.parseColor("#599BCC")),
            style = TextStyle(fontSize = 23.sp)

        )
    }

}
@Composable
fun ImagePrincipalPreguntasFrecuents(){
    Row(modifier = Modifier.fillMaxWidth(),Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.solicitud),
            contentDescription = "Preguntas Frecuentes")
    }
}

data class DataApp(val pregunta: String, val respuesta: String)


@Composable
fun ListaPreguntasFrecuentes(
    datos: List<DataApp>
){
    LazyColumn() {
        items(items = datos){ dato ->
            CardGeneral(pregunta = dato.pregunta, respuesta = dato.respuesta)
        }
    }
}

@Composable
private fun CardGeneral(
    pregunta: String,
    respuesta: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(pregunta,respuesta)
    }
}


@Composable
private fun CardContent(pregunta: String,respuesta: String) {

    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            //Text(text = "Hello, ")
            Text(
                text = pregunta,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = (respuesta),
                )
            }
        }

        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                //imageVector = if (expanded) Icons.Filled.ArrowBack else Icons.Filled.ArrowDropDown,
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }

    }
}


























/////////
@Composable
fun ImageAsIcon(
    imageResId: Int,
    contentDescription: String,
    size: Dp,
    modifier: Modifier = Modifier
) {
    val image: Painter = painterResource(imageResId)

    Image(
        painter = image,
        contentDescription = contentDescription,
        modifier = modifier.size(size)

    )
}






package no.nav.familie.klage.brev

import com.fasterxml.jackson.databind.JsonNode
import no.nav.familie.http.client.AbstractPingableRestClient
import no.nav.familie.klage.brev.dto.FritekstBrevRequestDto
import no.nav.familie.klage.felles.util.medContentTypeJsonUTF8
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.client.RestOperations
import java.net.URI

@Component
class BrevClient(
    @Value("\${FAMILIE_BREV_API_URL}")
    private val familieBrevUri: String,
    @Qualifier("utenAuth")
    private val restOperations: RestOperations
) : AbstractPingableRestClient(restOperations, "familie.brev") {

    override val pingUri: URI = URI.create("$familieBrevUri/api/status")

    override fun ping() {
        operations.optionsForAllow(pingUri)
    }

    fun genererHtmlFritekstbrev(fritekstBrev: FritekstBrevRequestDto, saksbehandlerNavn: String, enhet: String): String {
        val url = URI.create("$familieBrevUri/api/fritekst-brev/html")
        return postForEntity(
            url,
            FritekstBrevRequestMedSignatur(
                fritekstBrev,
                saksbehandlerNavn,
                enhet
            ),
            HttpHeaders().medContentTypeJsonUTF8()
        )
    }

    companion object {

        const val ef = "ef-brev"
        const val test = "testdata"
    }
}

data class BrevRequestMedSignaturer(
    val brevFraSaksbehandler: JsonNode,
    val saksbehandlersignatur: String,
    val enhet: String?,
    val skjulBeslutterSignatur: Boolean
)

data class FritekstBrevRequestMedSignatur(
    val brevFraSaksbehandler: FritekstBrevRequestDto,
    val saksbehandlersignatur: String,
    val enhet: String
)

package no.nav.familie.klage.brev.domain

import no.nav.familie.klage.brev.dto.Avsnitt
import no.nav.familie.klage.brev.dto.FritekstBrevtype
import no.nav.familie.klage.felles.domain.Sporbar
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Embedded
import java.util.UUID

data class BrevMedAvsnitt(
    val behandlingId: UUID,
    val overskrift: String,
    val avsnitt: List<Avsnitt>?,
    )

data class Brev(
    @Id
    val behandlingId: UUID,
    val overskrift: String,
    val saksbehandlerHtml: String,
    val brevtype: FritekstBrevtype,
    @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY)
    val sporbar: Sporbar = Sporbar()
)
enum class FormVilkår {
    OPPFYLT,
    IKKE_OPPFYLT,
    IKKE_SATT
}
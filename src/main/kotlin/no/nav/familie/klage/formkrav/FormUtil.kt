package no.nav.familie.klage.formkrav


import no.nav.familie.klage.formkrav.dto.FormDto
import java.time.LocalDate
import java.util.UUID

fun formDto(
    behandlingId: UUID = UUID.randomUUID(),
    fagsakId: UUID = UUID.randomUUID(),
    vedtaksdato: LocalDate = LocalDate.now(), // TODO: endre til mulig nullverdi
    klageMottatt: LocalDate = LocalDate.now(),
    klageaarsak: String = "Årsak",
    klageBeskrivelse: String = "jeg er sinna",
    sakSistEndret: LocalDate = LocalDate.now()
): FormDto =
    FormDto(
        behandlingId,
        fagsakId,
        vedtaksdato,
        klageMottatt,
        klageaarsak,
        klageBeskrivelse,
        sakSistEndret,
    )
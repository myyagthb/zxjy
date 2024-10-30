{{/*
Expand the name of the chart.
*/}}
{{- define "hxy.db.name" -}}
{{- default (print .Chart.Name "-db") .Values.db.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "hxy.db.fullname" -}}
{{- if .Values.db.fullnameOverride }}
{{- .Values.db.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default (print .Chart.Name "-db") .Values.db.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "hxy.db.selectorLabels" -}}
app.kubernetes.io/name: {{ include "hxy.db.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "hxy.db.serviceAccountName" -}}
{{- if .Values.db.serviceAccount.create }}
{{- default (include "hxy.db.fullname" .) .Values.db.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.db.serviceAccount.name }}
{{- end }}
{{- end }}

{{/*
Expand the name of the chart.
*/}}
{{- define "hxy.b.name" -}}
{{- default (print .Chart.Name "-b") .Values.b.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "hxy.b.fullname" -}}
{{- if .Values.b.fullnameOverride }}
{{- .Values.b.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default (print .Chart.Name "-b") .Values.b.nameOverride }}
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
{{- define "hxy.b.selectorLabels" -}}
app.kubernetes.io/name: {{ include "hxy.b.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "hxy.b.serviceAccountName" -}}
{{- if .Values.b.serviceAccount.create }}
{{- default (include "hxy.b.fullname" .) .Values.b.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.b.serviceAccount.name }}
{{- end }}
{{- end }}

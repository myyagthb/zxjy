{{/*
Expand the name of the chart.
*/}}
{{- define "hxy.f.name" -}}
{{- default (print .Chart.Name "-f") .Values.f.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "hxy.f.fullname" -}}
{{- if .Values.f.fullnameOverride }}
{{- .Values.f.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default (print .Chart.Name "-f") .Values.f.nameOverride }}
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
{{- define "hxy.f.selectorLabels" -}}
app.kubernetes.io/name: {{ include "hxy.f.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "hxy.f.serviceAccountName" -}}
{{- if .Values.f.serviceAccount.create }}
{{- default (include "hxy.f.fullname" .) .Values.f.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.f.serviceAccount.name }}
{{- end }}
{{- end }}

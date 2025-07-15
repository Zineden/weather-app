export function importImages(r: { keys: () => string[]; (id: string): string }): Record<string, string> {
    return r.keys().reduce((images: Record<string, string>, path: string) => {
        const key = path.replace('./', '').replace(/\.\w+$/, '');
        images[key] = r(path);
        return images;
    }, {});
};
